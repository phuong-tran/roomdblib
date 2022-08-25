package com.pt.room.lib.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.db.lib.entity.BaseEntity
@Entity(tableName = "student")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    val id: Long = 0,
    val name: String,
    val age: Int
)