package com.pt.room.lib.db

import com.db.lib.ddl.EntityFinderTemplate
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

class StudentEntityFinder constructor(
    private val studentDao: StudentDao
) : EntityFinderTemplate<Long, StudentEntity> {
    override suspend fun findById(id: Long): StudentEntity? {
      return studentDao.findById(id)
    }

    override suspend fun findWhereIdIn(ids: List<Long>): List<StudentEntity> {
        return studentDao.findWhereIdIn(ids)
    }

    override fun findWhereIdInAsFlow(ids: List<Long>): Flow<List<StudentEntity>> {
        return studentDao.findWhereIdInAsFlow(ids)
    }

    override fun findIdAsAsFlow(id: Long): Flow<StudentEntity?> {
        return studentDao.findByIdAsAsFlow(id)
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

    override fun findWhereIdInSingle(ids: List<Long>): Single<List<StudentEntity>> {
        return studentDao.findWhereIdInSingle(ids)
    }

    override fun findWhereIdInMayBe(ids: List<Long>): Maybe<List<StudentEntity>> {
        return studentDao.findWhereIdInMayBe(ids)
    }

    override suspend fun findAll(): List<StudentEntity> {
        return studentDao.findAll()
    }

    override fun findAllFlow(): Flow<List<StudentEntity>> {
        return studentDao.findAllFlow()
    }
}