package com.pt.room.lib.db

import com.db.lib.converter.EntityConverter
import com.pt.room.lib.model.StudentModel
import com.pt.room.lib.model.toStudentModel

class StudentEntityConverter : EntityConverter<StudentEntity, StudentModel> {
    override fun convert(from: StudentEntity): StudentModel {
        return from.toStudentModel
    }
}