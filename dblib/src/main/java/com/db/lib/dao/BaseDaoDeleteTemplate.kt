package com.db.lib.dao

import androidx.room.Delete
import androidx.room.Transaction
import com.db.lib.dml.EntityDeleteTemplate

interface BaseDaoDeleteTemplate<E> : EntityDeleteTemplate<E> {
    @Transaction
    @Delete
    override suspend fun delete(entity: E)

    @Transaction
    @Delete
    override suspend fun delete(vararg entities: E)

    @Transaction
    @Delete
    override suspend fun delete(entities: List<E>)
}
