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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

abstract class AbstractDaoProxyAdvanced<ID, E, A>(
    private val entityConverter: EntityConverter<E, A>,
    private val entityFinder: EntityFinderTemplate<ID, E>,
    private val entityInsertTemplate: EntityInsertTemplate<ID, E>,
    private val entityUpdateTemplate: EntityUpdateTemplate<E>,
    private val entityDeleteTemplate: EntityDeleteTemplate<ID, E>,
    config: Config = Config.defaultConfig
) : EntityDeleteTransformer<ID, E, A>,
    EntityUpdateTransformer<E, A>,
    EntityInsertTransformer<E, A> {
    private val sharedDataChangeStateFlow: MutableSharedFlow<RecordsChange<A>> =
        config.toMutableSharedFlow()
    val dataRecordChangeFlow: Flow<RecordsChange<A>> =
        sharedDataChangeStateFlow.asSharedFlow().distinctUntilChanged()

    val subscriptionCount: StateFlow<Int> get() = sharedDataChangeStateFlow.subscriptionCount
    val subscriptionCountValue: Int get() = subscriptionCount.value

    private fun RecordsChange<A>.notifyRecordChangeEventInternal(): RecordsChange<A> {
        return apply {
            mainCoroutineScope.launch {
                sharedDataChangeStateFlow.emit(this@apply)
            }
        }
    }

    @Suppress("unused")
    fun notifyRecordChangeEvent(record: RecordsChange<A>): RecordsChange<A> {
        return record.notifyRecordChangeEventInternal()
    }

    final override suspend fun delete(entity: E): A {
        val deletedRecord = entityConverter.convert(entity)
        entityDeleteTemplate.delete(entity)
        return deletedRecord.also { record ->
            RecordsChange.RecordDeleted(record).notifyRecordChangeEventInternal()
        }
    }

    final override suspend fun delete(vararg entities: E): Collection<A> {
        return delete(entities.toList())
    }

    final override suspend fun delete(entities: List<E>): Collection<A> {
        val deletedRecords = entityConverter.convert(entities.toList())
        entityDeleteTemplate.delete(entities)
        return deletedRecords.also {
            RecordsChange.RecordsDeleted(it).notifyRecordChangeEventInternal()
        }
    }

    final override fun deleteFlow(entity: E): Flow<A> {
        return flow {
            emit(delete(entity))
        }
    }

    final override fun deleteFlow(vararg entities: E): Flow<Collection<A>> {
        return flow {
            emit(delete(entities.toList()))
        }
    }

    final override fun deleteFlow(entities: List<E>): Flow<Collection<A>> {
        return flow {
            emit(delete(entities))
        }
    }

    final override fun deleteSingle(entity: E): Single<A> {
        return entityDeleteTemplate.deleteSingle(entity).map {
            entityConverter.convert(entity)
        }.doOnSuccess {
            RecordsChange.RecordDeleted(it).notifyRecordChangeEventInternal()
        }
    }

    final override fun deleteSingle(vararg entities: E): Single<Collection<A>> {
        return deleteSingle(entities.toList())
    }

    final override fun deleteSingle(entities: List<E>): Single<Collection<A>> {
        return entityDeleteTemplate.deleteSingle(entities).map {
            entityConverter.convert(entities)
        }.doOnSuccess {
            RecordsChange.RecordsDeleted(it).notifyRecordChangeEventInternal()
        }
    }

    final override fun deleteMayBe(entity: E): Maybe<A> {
        return entityDeleteTemplate.deleteMaybe(entity).map {
            entityConverter.convert(entity)
        }.doOnSuccess {
            RecordsChange.RecordDeleted(it).notifyRecordChangeEventInternal()
        }
    }

    final override fun deleteMayBe(entities: List<E>): Maybe<Collection<A>> {
        return entityDeleteTemplate.deleteMaybe(entities).map {
            entityConverter.convert(entities)
        }.doOnSuccess {
            RecordsChange.RecordsDeleted(it).notifyRecordChangeEventInternal()
        }
    }

    final override fun deleteMaybe(vararg entities: E): Maybe<Collection<A>> {
        return deleteMayBe(entities.toList())
    }

    final override suspend fun deleteAll(): Int {
        return entityDeleteTemplate.deleteAll().also {
            RecordsChange.RecordsDeletedAll.notifyRecordChangeEventInternal()
        }
    }

    final override fun deleteAllCompletable(): Completable {
        return entityDeleteTemplate.deleteAllCompletable().doOnComplete {
            RecordsChange.RecordsDeletedAll.notifyRecordChangeEventInternal()
        }
    }

    final override fun deleteAllFlow(): Flow<Int> {
        return entityDeleteTemplate.deleteAllFlow().map {
            RecordsChange.RecordsDeletedAll.notifyRecordChangeEventInternal()
            it
        }
    }

    final override fun deleteAllSingle(): Single<Int> {
        return entityDeleteTemplate.deleteAllSingle().doOnSuccess {
            RecordsChange.RecordsDeletedAll.notifyRecordChangeEventInternal()
        }
    }

    final override fun deleteAllMaybe(): Maybe<Int> {
        return entityDeleteTemplate.deleteAllMaybe().doOnSuccess {
            RecordsChange.RecordsDeletedAll.notifyRecordChangeEventInternal()
        }
    }

    final override suspend fun deleteById(id: ID): A? {
        val entity = entityFinder.findById(id)
        return entity?.let {
            return delete(it)
        }
    }

    final override suspend fun deleteByIds(vararg ids: ID): Collection<A> {
        return deleteByIds(ids.toList())
    }

    final override suspend fun deleteByIds(ids: List<ID>): Collection<A> {
        val entities = entityFinder.findByIds(ids)
        return if (entities.isNotEmpty()) {
            delete(entities)
        } else {
            listOf()
        }
    }

    final override fun deleteByIdFlow(id: ID): Flow<A?> {
        return flow {
            emit(deleteById(id))
        }
    }

    final override fun deleteByIdsFlow(vararg ids: ID): Flow<Collection<A>> {
        return deleteByIdsFlow(ids.toList())
    }

    final override fun deleteByIdsFlow(ids: List<ID>): Flow<Collection<A>> {
        return flow {
            emit(deleteByIds(ids))
        }
    }

    final override fun deleteByIdSingle(id: ID): Single<A> {
        return entityFinder.findByIdSingle(id).flatMap {
            deleteSingle(it)
        }
    }

    final override fun deleteByIdsSingle(vararg ids: ID): Single<Collection<A>> {
        return deleteByIdsSingle(ids.toList())
    }

    final override fun deleteByIdsSingle(ids: List<ID>): Single<Collection<A>> {
        return entityFinder.findByIdsSingle(ids).flatMap {
            deleteSingle(it)
        }
    }

    final override fun deleteByIdMayBe(id: ID): Maybe<A> {
        return entityFinder.findByIdMayBe(id).flatMap {
            deleteMayBe(it)
        }
    }

    final override fun deleteByIdsMaybe(vararg ids: ID): Maybe<Collection<A>> {
        return deleteByIdsMayBe(ids.toList())
    }

    final override fun deleteByIdsMayBe(ids: List<ID>): Maybe<Collection<A>> {
        return entityFinder.findByIdsMayBe(ids).flatMap {
            deleteMayBe(it)
        }
    }

    final override suspend fun insert(entity: E): A {
        val id = entityInsertTemplate.insert(entity)
        return entityFinder.findById(id)?.let { record ->
            entityConverter.convert(record).also { model ->
                RecordsChange.RecordInserted(model).notifyRecordChangeEventInternal()
            }
        } ?: throw SQLiteAbortException("Cannot get inserted record for $entity")
    }

    final override suspend fun insert(entities: List<E>): Collection<A> {
        val ids = entityInsertTemplate.insert(entities)
        return entityFinder.findByIds(ids.toList()).let { records ->
            entityConverter.convert(records).also { models ->
                RecordsChange.RecordsInserted(models).notifyRecordChangeEventInternal()
            }
        }
    }

    final override suspend fun insert(vararg entities: E): Collection<A> {
        return insert(entities.toList())
    }

    final override fun insertFlow(entity: E): Flow<A> {
        return flow {
            emit(insert(entity))
        }
    }

    final override fun insertFlow(entities: List<E>): Flow<Collection<A>> {
        return flow {
            emit(insert(entities))
        }
    }

    final override fun insertFlow(vararg entities: E): Flow<Collection<A>> {
        return insertFlow(entities.toList())
    }

    final override fun insertSingle(entity: E): Single<A> {
        return entityInsertTemplate.insertSingle(entity).flatMap { id ->
            entityFinder.findByIdSingle(id).map {
                entityConverter.convert(it)
            }.doOnSuccess {
                RecordsChange.RecordInserted(it).notifyRecordChangeEventInternal()
            }
        }
    }

    final override fun insertSingle(entities: List<E>): Single<Collection<A>> {
        return entityInsertTemplate.insertSingle(entities).flatMap { ids ->
            entityFinder.findByIdsSingle(ids).map {
                entityConverter.convert(it)
            }.doOnSuccess {
                RecordsChange.RecordsDeleted(it).notifyRecordChangeEventInternal()
            }
        }
    }

    final override fun insertSingle(vararg entities: E): Single<Collection<A>> {
        return insertSingle(entities.toList())
    }

    final override fun insertMaybe(entity: E): Maybe<A> {
        return entityInsertTemplate.insertMaybe(entity).flatMap { id ->
            entityFinder.findByIdMayBe(id).map {
                entityConverter.convert(it)
            }.doOnSuccess {
                RecordsChange.RecordInserted(it).notifyRecordChangeEventInternal()
            }
        }
    }

    final override fun insertMaybe(entities: List<E>): Maybe<Collection<A>> {
        return entityInsertTemplate.insertMaybe(entities).flatMap { ids ->
            entityFinder.findByIdsMayBe(ids).map {
                entityConverter.convert(it)
            }.doOnSuccess {
                RecordsChange.RecordsInserted(it).notifyRecordChangeEventInternal()
            }
        }
    }

    final override fun insertMaybe(vararg entities: E): Maybe<Collection<A>> {
        return insertMaybe(entities.toList())
    }

    final override suspend fun update(entity: E): A {
        entityUpdateTemplate.update(entity)
        return entityConverter.convert(entity).also { model ->
            RecordsChange.RecordUpdated(model).notifyRecordChangeEventInternal()
        }
    }

    final override suspend fun update(vararg entities: E): Collection<A> {
        return update(entities.toList())
    }

    final override suspend fun update(entities: List<E>): Collection<A> {
        entityUpdateTemplate.update(entities)
        return entityConverter.convert(entities).also { models ->
            RecordsChange.RecordsUpdated(models).notifyRecordChangeEventInternal()
        }
    }

    final override fun updateFlow(entity: E): Flow<A> {
        return flow {
            emit(update(entity))
        }
    }

    final override fun updateFlow(vararg entities: E): Flow<Collection<A>> {
        return flow {
            emit(update(entities.toList()))
        }
    }

    final override fun updateFlow(entities: List<E>): Flow<Collection<A>> {
        return flow {
            emit(update(entities))
        }
    }

    final override fun updateSingle(entity: E): Single<A> {
        return entityUpdateTemplate.updateSingle(entity).map {
            entityConverter.convert(entity)
        }.doOnSuccess {
            RecordsChange.RecordUpdated(it).notifyRecordChangeEventInternal()
        }
    }

    final override fun updateSingle(entities: List<E>): Single<Collection<A>> {
        return entityUpdateTemplate.updateSingle(entities).map {
            entityConverter.convert(entities)
        }.doOnSuccess {
            RecordsChange.RecordsUpdated(it).notifyRecordChangeEventInternal()
        }
    }

    final override fun updateSingle(vararg entities: E): Single<Collection<A>> {
        return updateSingle(entities.toList())
    }

    final override fun updateMaybe(entity: E): Maybe<A> {
        return entityUpdateTemplate.updateMaybe(entity).map {
            entityConverter.convert(entity)
        }.doOnSuccess {
            RecordsChange.RecordUpdated(it).notifyRecordChangeEventInternal()
        }
    }

    final override fun updateMaybe(entities: List<E>): Maybe<Collection<A>> {
        return entityUpdateTemplate.updateMaybe(entities).map {
            entityConverter.convert(entities)
        }.doOnSuccess {
            RecordsChange.RecordsUpdated(it).notifyRecordChangeEventInternal()
        }
    }

    final override fun updateMaybe(vararg entities: E): Maybe<Collection<A>> {
        return updateMaybe(entities.toList())
    }

    private companion object {
        private val mainCoroutineScope: CoroutineScope by lazy {
            CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        }
    }
}