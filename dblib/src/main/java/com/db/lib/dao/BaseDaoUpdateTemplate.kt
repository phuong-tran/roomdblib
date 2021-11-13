package com.db.lib.dao

import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update
import com.db.lib.dml.EntityUpdateTemplate

interface BaseDaoUpdateTemplate<ID, E> : EntityUpdateTemplate<E> {
    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun update(entity: E)

    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun update(vararg entities: E)

    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun update(entities: List<E>)
}