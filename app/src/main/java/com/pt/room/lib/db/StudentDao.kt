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
interface StudentDao : BaseDao<Long, StudentEntity> {
    @Query(FIND_BY_ID)
    override suspend fun findById(id: Long): StudentEntity?

    @Query(FIND_BY_ID)
    fun findByIdAsAsFlow(id: Long): Flow<StudentEntity?>

    @Query(FIND_ALL_WHERE_ID_IN)
    override suspend fun findWhereIdIn(ids: List<Long>): List<StudentEntity>

    @Query(FIND_ALL_WHERE_ID_IN)
    override fun findWhereIdInFlow(ids: List<Long>): Flow<List<StudentEntity>>

    @Query(FIND_ALL)
    override suspend fun findAll(): List<StudentEntity>

    @Query(FIND_ALL)
    override fun findAllFlow(): Flow<List<StudentEntity>>

    @Query(FIND_ALL)
    override fun findAllSingle(): Single<List<StudentEntity>>

    @Query(FIND_ALL)
    override fun findAllMayBe(): Maybe<List<StudentEntity>>

    @Query(FIND_BY_ID)
    override fun findByIdSingle(id: Long): Single<StudentEntity>

    @Query(FIND_BY_ID)
    override fun findByIdMayBe(id: Long): Maybe<StudentEntity>

    @Query(FIND_ALL_WHERE_ID_IN)
    override fun findWhereIdInSingle(ids: List<Long>): Single<List<StudentEntity>>

    @Query(FIND_ALL_WHERE_ID_IN)
    override fun findWhereIdInMayBe(ids: List<Long>): Maybe<List<StudentEntity>>

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
        private const val FIND_ALL = "SELECT * FROM StudentEntity"
        private const val FIND_BY_ID = "SELECT * FROM StudentEntity WHERE id =:id"
        private const val FIND_ALL_WHERE_ID_IN = "SELECT * FROM StudentEntity WHERE id IN(:ids)"
        private const val DELETE_ALL = "DELETE FROM StudentEntity"
    }
}