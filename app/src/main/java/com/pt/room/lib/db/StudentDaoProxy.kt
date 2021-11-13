package com.pt.room.lib.db

import com.db.lib.observable.RecordsChange
import com.db.lib.observable.advanced.AbstractRoomDBAdvancedTemplateV2
import com.pt.room.lib.model.StudentModel

class StudentDaoProxy (
    private val dao: StudentDao,
) : AbstractRoomDBAdvancedTemplateV2<Long, StudentEntity, StudentModel>(dao) {

    suspend fun deleteAll() {
        dao.deleteAll().also {
            RecordsChange.RecordsDeletedAll.notifyRecordChangeEvent()
        }
    }
}