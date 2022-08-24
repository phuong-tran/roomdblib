package com.pt.room.lib.db

import com.db.lib.ddl.EntityFinderTemplate
import com.db.lib.entity.LookupEntity
import com.db.lib.proxy.RecordsChange
import com.db.lib.proxy.advanced.AbstractDaoProxyAdvanced
import com.pt.room.lib.model.StudentModel

class StudentDaoProxy (
    entityFinder: EntityFinderTemplate<Long, StudentEntity>,
    entityConverter: StudentEntityConverter,
    private val dao: StudentDao,
) : AbstractDaoProxyAdvanced<Long, StudentEntity, StudentModel>(
    entityConverter = entityConverter,
    entityFinder = entityFinder,
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