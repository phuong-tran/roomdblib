package com.pt.room.lib.db

import androidx.room.Dao
import androidx.room.Query
import com.db.lib.converter.EntityConverter
import com.db.lib.dao.template.BaseDaoWithLookupAndConverterEntityTemplate
import com.pt.room.lib.model.StudentModel
import io.reactivex.rxjava3.core.Completable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val FIND_ALL = "SELECT * FROM StudentEntity"
private const val FIND_BY_ID = "SELECT * FROM StudentEntity WHERE id =:id"
private const val FIND_ALL_WHERE_ID_IN = "SELECT * FROM StudentEntity WHERE id IN(:ids)"
private const val DELETE_ALL = "DELETE FROM StudentEntity"

@Dao
interface StudentDao :
    BaseDaoWithLookupAndConverterEntityTemplate<Long, StudentEntity, StudentModel> {
    @Query(FIND_BY_ID)
    suspend fun findById(id: Long): StudentEntity?

    @Query(FIND_BY_ID)
    fun findByIdAsAsFlow(id: Long): Flow<StudentEntity?>

    @Query(FIND_ALL)
    suspend fun findAll(): List<StudentEntity>

    @Query(FIND_ALL)
    fun findAllFlow(): Flow<List<StudentEntity>>

    @Query(FIND_ALL_WHERE_ID_IN)
    suspend fun findWhereIdIn(ids: List<Long>): List<StudentEntity>

    @Query(FIND_ALL_WHERE_ID_IN)
    fun findWhereIdInAsFlow(ids: List<Long>): Flow<List<StudentEntity>>

    @Query(DELETE_ALL)
    override suspend fun deleteAll(): Int

    @Query(DELETE_ALL)
    override fun deleteAllCompletable(): Completable

    override fun deleteAllFlow(): Flow<Int> = flow {
        emit(deleteAll())
    }

    override val lookupEntity
        get() = StudentEntityLookup(
            this
        )

    override val entityConverter: EntityConverter<StudentEntity, StudentModel>
        get() = StudentEntityConverter()
}