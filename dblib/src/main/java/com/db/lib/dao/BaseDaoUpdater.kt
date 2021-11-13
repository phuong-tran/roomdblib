package com.db.lib.dao

import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update
import com.db.lib.dml.EntityUpdater

interface BaseDaoUpdater<ID, E> : EntityUpdater<E> {
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