package com.db.lib.proxy.advanced

import android.database.sqlite.SQLiteAbortException
import com.db.lib.converter.EntityConverter
import com.db.lib.ddl.EntityFinderTemplate
import com.db.lib.dml.EntityDeleteTemplate
import com.db.lib.dml.EntityInsertTemplate
import com.db.lib.dml.EntityUpdateTemplate
import com.db.lib.proxy.Config
import com.db.lib.proxy.Config.Companion.toMutableSharedFlow
import com.db.lib.proxy.RecordsChange
import com.db.lib.transformer.EntityDeleteTransformer
import com.db.lib.transformer.EntityInsertTransformer
import com.db.lib.transformer.EntityUpdateTransformer
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow

abstract class AbstractDaoProxyAdvanced<ID, E, A>(
    private val entityConverter: EntityConverter<E, A>,
    private val entityFinder: EntityFinderTemplate<ID, E>,
    private val entityInsertTemplate: EntityInsertTemplate<ID, E>,
    private val entityUpdateTemplate: EntityUpdateTemplate<E>,
    private val entityDeleteTemplate: EntityDeleteTemplate<E>,
    config: Config = Config.defaultConfig
) : EntityConverter<E, A> by entityConverter,
    EntityDeleteTransformer<E, A>,
    EntityUpdateTransformer<E, A>,
    EntityInsertTransformer<E, A> {

    private val sharedDataChangeStateFlow: MutableSharedFlow<RecordsChange<A>> =
        config.toMutableSharedFlow()
    val dataRecordChangeFlow: Flow<RecordsChange<A>> =
        sharedDataChangeStateFlow.asSharedFlow().distinctUntilChanged()

    val subscriptionCount: StateFlow<Int> get() = sharedDataChangeStateFlow.subscriptionCount
    val subscriptionCountValue: Int get() = subscriptionCount.value

    private suspend fun RecordsChange<A>.notifyRecordChangeEventInternal(): RecordsChange<A> {
        return apply {
            sharedDataChangeStateFlow.emit(this)
        }
    }

    private fun RecordsChange<A>.tryNotifyRecordChangeEventInternal(): RecordsChange<A> {
        return apply {
            sharedDataChangeStateFlow.tryEmit(this)
        }
    }


    suspend fun notifyRecordChangeEvent(record: RecordsChange<A>): RecordsChange<A> {
        return record.notifyRecordChangeEventInternal()
    }

    override suspend fun delete(entity: E): A {
        val deletedRecord = convert(entity)
        entityDeleteTemplate.delete(entity)
        return deletedRecord.also { record ->
            RecordsChange.RecordDeleted(record).notifyRecordChangeEventInternal()
        }
    }

    override suspend fun delete(vararg entities: E): Collection<A> {
        return delete(entities.toList())
    }

    override suspend fun delete(entities: List<E>): Collection<A> {
        val deletedRecords = convert(entities.toList())
        entityDeleteTemplate.delete(entities)
        return deletedRecords.also {
            RecordsChange.RecordsDeleted(it).notifyRecordChangeEventInternal()
        }
    }

    override fun deleteFlow(entity: E): Flow<A> {
        return flow {
            emit(delete(entity))
        }
    }

    override fun deleteFlow(vararg entities: E): Flow<Collection<A>> {
        return flow {
            emit(delete(entities.toList()))
        }
    }

    override fun deleteFlow(entities: List<E>): Flow<Collection<A>> {
        return flow {
            emit(delete(entities))
        }
    }

    override fun deleteSingle(entity: E): Single<A> {
        return entityDeleteTemplate.deleteSingle(entity).map {
            convert(entity)
        }.doOnSuccess {
            RecordsChange.RecordDeleted(it).tryNotifyRecordChangeEventInternal()
        }
    }

    override fun deleteSingle(vararg entities: E): Single<Collection<A>> {
        return deleteSingle(entities.toList())
    }

    override fun deleteSingle(entities: List<E>): Single<Collection<A>> {
        return entityDeleteTemplate.deleteSingle(entities).map {
            convert(entities)
        }.doOnSuccess {
            RecordsChange.RecordsDeleted(it).tryNotifyRecordChangeEventInternal()
        }
    }

    override fun deleteMayBe(entity: E): Maybe<A> {
        return entityDeleteTemplate.deleteMaybe(entity).map {
            convert(entity)
        }.doOnSuccess {
            RecordsChange.RecordDeleted(it).tryNotifyRecordChangeEventInternal()
        }
    }

    override fun deleteMayBe(entities: List<E>): Maybe<Collection<A>> {
        return entityDeleteTemplate.deleteMaybe(entities).map {
            convert(entities)
        }.doOnSuccess {
            RecordsChange.RecordsDeleted(it).tryNotifyRecordChangeEventInternal()
        }
    }


    override fun deleteMaybe(vararg entities: E): Maybe<Collection<A>> {
        return deleteMayBe(entities.toList())
    }

    override suspend fun deleteAll(): Int {
        return entityDeleteTemplate.deleteAll().also {
            RecordsChange.RecordsDeletedAll.notifyRecordChangeEventInternal()
        }
    }

    override fun deleteAllCompletable(): Completable {
        return entityDeleteTemplate.deleteAllCompletable().doOnComplete {
            RecordsChange.RecordsDeletedAll.tryNotifyRecordChangeEventInternal()
        }
    }

    override fun deleteAllFlow(): Flow<Int> {
        return flow {
            entityDeleteTemplate.deleteAllFlow().collect {
                RecordsChange.RecordsDeletedAll.notifyRecordChangeEventInternal()
            }
        }
    }

    override fun deleteAllSingle(): Single<Int> {
        return entityDeleteTemplate.deleteAllSingle().doOnSuccess {
            RecordsChange.RecordsDeletedAll.tryNotifyRecordChangeEventInternal()
        }
    }

    override fun deleteAllMaybe(): Maybe<Int> {
        return entityDeleteTemplate.deleteAllMaybe().doOnSuccess {
            RecordsChange.RecordsDeletedAll.tryNotifyRecordChangeEventInternal()
        }
    }

    override suspend fun insert(entity: E): A {
        val id = entityInsertTemplate.insert(entity)
        return entityFinder.findById(id)?.let { record ->
            convert(record).also { model ->
                RecordsChange.RecordInserted(model).notifyRecordChangeEventInternal()
            }
        } ?: throw SQLiteAbortException("Cannot get inserted record for $entity")
    }

    override suspend fun insert(entities: List<E>): Collection<A> {
        val ids = entityInsertTemplate.insert(entities)
        return entityFinder.findWhereIdIn(ids.toList()).let { records ->
            convert(records).also { models ->
                RecordsChange.RecordsInserted(models).notifyRecordChangeEventInternal()
            }
        }
    }

    override suspend fun insert(vararg entities: E): Collection<A> {
        return insert(entities.toList())
    }

    override fun insertFlow(entity: E): Flow<A> {
        return flow {
            emit(insert(entity))
        }
    }

    override fun insertFlow(entities: List<E>): Flow<Collection<A>> {
        return flow {
            emit(insert(entities))
        }
    }

    override fun insertFlow(vararg entities: E): Flow<Collection<A>> {
        return insertFlow(entities.toList())
    }

    override fun insertSingle(entity: E): Single<A> {
        return entityInsertTemplate.insertSingle(entity).flatMap { id ->
            entityFinder.findByIdSingle(id).map {
                convert(it)
            }.doOnSuccess {
                RecordsChange.RecordInserted(it).tryNotifyRecordChangeEventInternal()
            }
        }
    }

    override fun insertSingle(entities: List<E>): Single<Collection<A>> {
        return entityInsertTemplate.insertSingle(entities).flatMap { ids ->
            entityFinder.findWhereIdInSingle(ids).map {
                convert(it)
            }.doOnSuccess {
                RecordsChange.RecordsDeleted(it).tryNotifyRecordChangeEventInternal()
            }
        }
    }

    override fun insertSingle(vararg entities: E): Single<Collection<A>> {
        return insertSingle(entities.toList())
    }

    override fun insertMaybe(entity: E): Maybe<A> {
        return entityInsertTemplate.insertMaybe(entity).flatMap { id ->
            entityFinder.findByIdMayBe(id).map {
                convert(it)
            }.doOnSuccess {
                RecordsChange.RecordInserted(it).tryNotifyRecordChangeEventInternal()
            }
        }
    }

    override fun insertMaybe(entities: List<E>): Maybe<Collection<A>> {
        return entityInsertTemplate.insertMaybe(entities).flatMap { ids ->
            entityFinder.findWhereIdInMayBe(ids).map {
                convert(it)
            }.doOnSuccess {
                RecordsChange.RecordsInserted(it).tryNotifyRecordChangeEventInternal()
            }
        }
    }

    override fun insertMaybe(vararg entities: E): Maybe<Collection<A>> {
        return insertMaybe(entities.toList())
    }

    override suspend fun update(entity: E): A {
        entityUpdateTemplate.update(entity)
        return convert(entity).also { model ->
            RecordsChange.RecordUpdated(model).notifyRecordChangeEventInternal()
        }
    }

    override suspend fun update(vararg entities: E): Collection<A> {
        return update(entities.toList())
    }

    override suspend fun update(entities: List<E>): Collection<A> {
        entityUpdateTemplate.update(entities)
        return convert(entities).also { models ->
            RecordsChange.RecordsUpdated(models).notifyRecordChangeEventInternal()
        }
    }

    override fun updateAsFlow(entity: E): Flow<A> {
        return flow {
            emit(update(entity))
        }
    }

    override fun updateAsFlow(vararg entities: E): Flow<Collection<A>> {
        return flow {
            emit(update(entities.toList()))
        }
    }

    override fun updateAsFlow(entities: List<E>): Flow<Collection<A>> {
        return flow {
            emit(update(entities))
        }
    }
}