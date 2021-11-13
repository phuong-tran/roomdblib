package com.pt.room.lib.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [StudentEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class StudentDataBase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}