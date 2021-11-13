package com.db.lib.observable.advanced

import android.database.sqlite.SQLiteAbortException
import com.db.lib.dml.EntityDeleteTemplate
import com.db.lib.dml.EntityInsertTemplate
import com.db.lib.dml.LookupEntity
import com.db.lib.dml.EntityUpdateTemplate
import com.db.lib.converter.EntityConverter
import com.db.lib.observable.Config
import com.db.lib.observable.Config.Companion.toMutableSharedFlow
import com.db.lib.observable.RecordsChange
import com.db.lib.transformer.EntityDeleteTransformer
import com.db.lib.transformer.EntityInsertTransformer
import com.db.lib.transformer.EntityUpdateTransformer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow

abstract class AbstractRoomDBAdvanced<ID, E, A>(
    private val entityConverter: EntityConverter<E, A>,
    private val lookupEntity: LookupEntity<ID, E>,
    private val entityInsertTemplate: EntityInsertTemplate<ID, E>,
    private val entityUpdateTemplate: EntityUpdateTemplate<E>,
    private val entityDeleteTemplate: EntityDeleteTemplate<E>,
    config: Config = Config.defaultConfig
) : EntityConverter<E, A> by entityConverter,
    LookupEntity<ID, E> by lookupEntity,
    EntityDeleteTransformer<E, A>,
    EntityUpdateTransformer<E, A>,
    EntityInsertTransformer<E, A> {

    private val sharedDataChangeStateFlow: MutableSharedFlow<RecordsChange<A>> = config.toMutableSharedFlow()
    val dataRecordChangeFlow: Flow<RecordsChange<A>> =
        sharedDataChangeStateFlow.asSharedFlow().distinctUntilChanged()

    val subscriptionCount: StateFlow<Int> get() = sharedDataChangeStateFlow.subscriptionCount
    val subscriptionCountValue: Int get() = subscriptionCount.value

    protected suspend fun RecordsChange<A>.notifyRecordChangeEvent(): RecordsChange<A> {
        return apply {
            sharedDataChangeStateFlow.emit(this)
        }
    }

    override suspend fun delete(entity: E): A {
        val deletedRecord = convert(entity)
        entityDeleteTemplate.delete(entity)
        return deletedRecord.also { record ->
            RecordsChange.RecordDeleted(record).notifyRecordChangeEvent()
        }
    }

    override suspend fun delete(vararg entities: E): Collection<A> {
        return delete(entities.toList())
    }

    override suspend fun delete(entities: List<E>): Collection<A> {
        val deletedRecords = convert(entities.toList())
        entityDeleteTemplate.delete(entities)
        return deletedRecords.also {
            RecordsChange.RecordsDeleted(it).notifyRecordChangeEvent()
        }
    }

    override fun deleteAsFlow(entity: E): Flow<A> {
        return flow {
            emit(delete(entity))
        }
    }

    override fun deleteAsFlow(vararg entities: E): Flow<Collection<A>> {
        return flow {
            emit(delete(entities.toList()))
        }
    }

    override fun deleteAsFlow(entities: List<E>): Flow<Collection<A>> {
        return flow {
            emit(delete(entities))
        }
    }

    override suspend fun insert(entity: E): A {
        val id = entityInsertTemplate.insert(entity)
        return lookupEntity.fetchById(id)?.let { record ->
            convert(record).also { model ->
                RecordsChange.RecordInserted(model).notifyRecordChangeEvent()
            }
        } ?: throw SQLiteAbortException("Cannot get inserted record for $entity")
    }

    override suspend fun insert(entities: List<E>): Collection<A> {
        val ids = entityInsertTemplate.insert(entities)
        return lookupEntity.fetchWhereIdIn(ids.toList()).let { records ->
            convert(records).also { models ->
                RecordsChange.RecordsInserted(models).notifyRecordChangeEvent()
            }
        }
    }

    override suspend fun insert(vararg entities: E): Collection<A> {
        return insert(entities.toList())
    }

    override fun insertAsFlow(entity: E): Flow<A> {
        return flow {
            emit(insert(entity))
        }
    }

    override fun insertAsFlow(entities: List<E>): Flow<Collection<A>> {
        return flow {
            emit(insert(entities))
        }
    }

    override fun insertAsFlow(vararg entities: E): Flow<Collection<A>> {
        return insertAsFlow(entities.toList())
    }

    override suspend fun update(entity: E): A {
        entityUpdateTemplate.update(entity)
        return convert(entity).also { model ->
            RecordsChange.RecordUpdated(model).notifyRecordChangeEvent()
        }
    }

    override suspend fun update(vararg entities: E): Collection<A> {
        return update(entities.toList())
    }

    override suspend fun update(entities: List<E>): Collection<A> {
        entityUpdateTemplate.update(entities)
        return convert(entities).also { models ->
            RecordsChange.RecordsUpdated(models).notifyRecordChangeEvent()
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