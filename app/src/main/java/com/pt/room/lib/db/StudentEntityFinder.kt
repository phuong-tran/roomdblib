package com.pt.room.lib.db

import com.db.lib.ddl.EntityFinderTemplate
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

/**
 * Work around: https://issuetracker.google.com/issues/160258066
 */
class StudentEntityFinder constructor(
    private val studentDao: StudentDao
) : EntityFinderTemplate<Long, StudentEntity> {
    override suspend fun findById(id: Long): StudentEntity? {
      return studentDao.findById(id)
    }

    override suspend fun findByIds(ids: List<Long>): List<StudentEntity> {
        return studentDao.findWhereIdIn(ids)
    }

    override fun findByIdsFlow(ids: List<Long>): Flow<List<StudentEntity>> {
        return studentDao.findWhereIdInFlow(ids)
    }

    override fun findByIdFlow(id: Long): Flow<StudentEntity?> {
        return studentDao.findByIdFlow(id)
    }

    override fun findAllSingle(): Single<List<StudentEntity>> {
        return studentDao.findAllSingle()
    }

    override fun findAllMayBe(): Maybe<List<StudentEntity>> {
        return studentDao.findAllMayBe()
    }

    override fun findByIdSingle(id: Long): Single<StudentEntity> {
        return studentDao.findByIdSingle(id)
    }

    override fun findByIdMayBe(id: Long): Maybe<StudentEntity> {
        return studentDao.findByIdMayBe(id)
    }

    override fun findByIdsSingle(ids: List<Long>): Single<List<StudentEntity>> {
        return studentDao.findWhereIdInSingle(ids)
    }

    override fun findByIdsMayBe(ids: List<Long>): Maybe<List<StudentEntity>> {
        return studentDao.findWhereIdInMayBe(ids)
    }

    override suspend fun findAll(): List<StudentEntity> {
        return studentDao.findAll()
    }

    override fun findAllFlow(): Flow<List<StudentEntity>> {
        return studentDao.findAllFlow()
    }
}