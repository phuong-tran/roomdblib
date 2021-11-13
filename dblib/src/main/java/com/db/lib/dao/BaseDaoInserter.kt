package com.db.lib.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.db.lib.dml.EntityInserter

interface BaseDaoInserter<ID, E> : EntityInserter<ID, E> {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(entity: E): ID

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(entities: List<E>): Array<ID>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(vararg entities: E): Array<ID>
}
