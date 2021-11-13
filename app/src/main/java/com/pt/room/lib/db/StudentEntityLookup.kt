package com.pt.room.lib.db

import com.db.lib.dml.LookupEntity
import kotlinx.coroutines.flow.Flow

class StudentEntityLookup constructor(
    private val studentDao: StudentDao
) : LookupEntity<Long, StudentEntity> {
    override suspend fun fetchById(id: Long): StudentEntity? {
      return studentDao.findById(id)
    }

    override suspend fun fetchWhereIdIn(ids: List<Long>): List<StudentEntity> {
        return studentDao.findWhereIdIn(ids)
    }

    override fun fetchWhereIdInAsFlow(ids: List<Long>): Flow<List<StudentEntity>> {
        return studentDao.findWhereIdInAsFlow(ids)
    }

    override fun fetchByIdAsAsFlow(id: Long): Flow<StudentEntity?> {
        return studentDao.findByIdAsAsFlow(id)
    }
}