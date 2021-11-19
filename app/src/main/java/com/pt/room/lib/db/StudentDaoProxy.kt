package com.pt.room.lib.db

import com.db.lib.proxy.RecordsChange
import com.db.lib.proxy.advanced.AbstractDaoProxyAdvancedTemplateV2
import com.pt.room.lib.model.StudentModel

class StudentDaoProxy (
    private val dao: StudentDao,
) : AbstractDaoProxyAdvancedTemplateV2<Long, StudentEntity, StudentModel>(dao) {

    suspend fun deleteAll() {
        dao.deleteAll().also {
            notifyRecordChangeEvent(RecordsChange.RecordsDeletedAll)
        }
    }
}