package com.pt.room.lib.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.db.lib.entity.BaseEntity
@Entity
data class StudentEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    override val id: Long = 0,
    val name: String,
    val age: Int
): BaseEntity<Long>