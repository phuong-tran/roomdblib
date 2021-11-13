package com.pt.room.lib.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.db.lib.converter.EntityConverter
import com.db.lib.dao.template.BaseDaoWithLookupAndConverterEntityTemplate
import com.db.lib.dml.LookupEntity
import com.pt.room.lib.model.StudentModel
import kotlinx.coroutines.flow.Flow

private const val FIND_ALL = "SELECT * FROM studentEntity"
private const val FIND_BY_ID = "SELECT * FROM studentEntity WHERE id =:id"
private const val FIND_ALL_WHERE_ID_IN = "SELECT * FROM studentEntity WHERE id IN(:ids)"
private const val DELETE_ALL = "DELETE FROM studentEntity"

@Dao
interface StudentDao : BaseDaoWithLookupAndConverterEntityTemplate<Long, StudentEntity, StudentModel> {
    @Query(FIND_BY_ID)
    suspend fun findById(id: Long): StudentEntity?

    @Query(FIND_BY_ID)
    fun findByIdAsAsFlow(id: Long): Flow<StudentEntity?>

    @Query(FIND_ALL)
    suspend fun findAll() : List<StudentEntity>

    @Query(FIND_ALL)
    fun findAllFlow() : Flow<List<StudentEntity>>

    @Query(FIND_ALL_WHERE_ID_IN)
    suspend fun findWhereIdIn(ids: List<Long>): List<StudentEntity>

    @Query(FIND_ALL_WHERE_ID_IN)
    fun findWhereIdInAsFlow(ids: List<Long>): Flow<List<StudentEntity>>

    @Query(DELETE_ALL)
    suspend fun deleteAll()

     override val lookupEntity: LookupEntity<Long, StudentEntity> get() {
        return object : LookupEntity<Long, StudentEntity> {
            override suspend fun fetchById(id: Long): StudentEntity? {
                return findById(id)
            }

            override suspend fun fetchWhereIdIn(ids: List<Long>): List<StudentEntity> {
                return findWhereIdIn(ids)
            }

            override fun fetchWhereIdInAsFlow(ids: List<Long>): Flow<List<StudentEntity>> {
                return findWhereIdInAsFlow(ids)
            }

            override fun fetchByIdAsAsFlow(id: Long): Flow<StudentEntity?> {
                return findByIdAsAsFlow(id)
            }
        }
    }

    override val entityConverter: EntityConverter<StudentEntity, StudentModel>
        get() = StudentEntityConverter()
}