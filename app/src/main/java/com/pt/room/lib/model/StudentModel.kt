package com.pt.room.lib.model

import com.pt.room.lib.db.StudentEntity

data class StudentModel(
    val id: Long = 0,
    val name: String,
    val age: Int
)

inline val StudentEntity.toStudentModel get() = StudentModel(id, name, age)

inline val StudentModel.toStudentEntity get() = StudentEntity(id, name, age)