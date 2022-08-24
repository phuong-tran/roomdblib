package com.pt.room.lib.db

import com.db.lib.entity.LookupEntity
import com.db.lib.proxy.RecordsChange
import com.db.lib.proxy.advanced.AbstractDaoProxyAdvanced
import com.pt.room.lib.model.StudentModel

class StudentDaoProxy (
    lookupEntity: LookupEntity<Long, StudentEntity>,
    entityConverter: StudentEntityConverter,
    private val dao: StudentDao,
) : AbstractDaoProxyAdvanced<Long, StudentEntity, StudentModel>(
    entityConverter = entityConverter,
    lookupEntity = lookupEntity,
    entityInsertTemplate = dao,
    entityUpdateTemplate = dao,
    entityDeleteTemplate = dao,

) {
    suspend fun deleteAll() {
        dao.deleteAll().also {
            notifyRecordChangeEvent(RecordsChange.RecordsDeletedAll)
        }
    }
}