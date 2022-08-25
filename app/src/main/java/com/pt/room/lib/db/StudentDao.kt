package com.pt.room.lib.db

import androidx.room.Dao
import androidx.room.Query
import com.db.lib.dao.BaseDao
import com.db.lib.dao.template.BaseDaoTemplate
import com.db.lib.ddl.EntityFinderTemplate
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Dao
interface StudentDao : BaseDaoTemplate<Long, StudentEntity> {
    @Query(FIND_BY_ID)
    suspend fun findById(id: Long): StudentEntity?

    @Query(FIND_BY_ID)
    fun findByIdFlow(id: Long): Flow<StudentEntity?>

    @Query(FIND_ALL_WHERE_ID_IN)
    suspend fun findWhereIdIn(ids: List<Long>): List<StudentEntity>

    @Query(FIND_ALL_WHERE_ID_IN)
    fun findWhereIdInFlow(ids: List<Long>): Flow<List<StudentEntity>>

    @Query(FIND_ALL)
    suspend fun findAll(): List<StudentEntity>

    @Query(FIND_ALL)
    fun findAllFlow(): Flow<List<StudentEntity>>

    @Query(FIND_ALL)
    fun findAllSingle(): Single<List<StudentEntity>>

    @Query(FIND_ALL)
    fun findAllMayBe(): Maybe<List<StudentEntity>>

    @Query(FIND_BY_ID)
    fun findByIdSingle(id: Long): Single<StudentEntity>

    @Query(FIND_BY_ID)
    fun findByIdMayBe(id: Long): Maybe<StudentEntity>

    @Query(FIND_ALL_WHERE_ID_IN)
    fun findWhereIdInSingle(ids: List<Long>): Single<List<StudentEntity>>

    @Query(FIND_ALL_WHERE_ID_IN)
    fun findWhereIdInMayBe(ids: List<Long>): Maybe<List<StudentEntity>>

    @Query(DELETE_ALL)
    override suspend fun deleteAll(): Int

    @Query(DELETE_ALL)
    override fun deleteAllCompletable(): Completable
    override fun deleteAllFlow(): Flow<Int> = flow {
        emit(deleteAll())
    }

    @Query(DELETE_ALL)
    override fun deleteAllSingle(): Single<Int>

    @Query(DELETE_ALL)
    override fun deleteAllMaybe(): Maybe<Int>

    companion object {
        private const val FIND_ALL = "SELECT * FROM student"
        private const val FIND_BY_ID = "SELECT * FROM student WHERE id =:id"
        private const val FIND_ALL_WHERE_ID_IN = "SELECT * FROM student WHERE id IN(:ids)"
        private const val DELETE_ALL = "DELETE FROM student"
    }
}