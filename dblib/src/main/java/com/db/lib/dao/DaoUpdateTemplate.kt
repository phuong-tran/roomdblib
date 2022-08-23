package com.db.lib.dao

import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update
import com.db.lib.dml.EntityUpdateTemplate
import io.reactivex.rxjava3.core.Completable

interface DaoUpdateTemplate<ID, E> : EntityUpdateTemplate<E> {
    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun update(entity: E) : Int

    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun update(vararg entities: E) : Int

    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun update(entities: List<E>) : Int

    // RX
    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    override fun updateCompletable(entity: E): Completable
    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    override fun updateCompletable(vararg entities: E): Completable
    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    override fun updateCompletable(entities: List<E>): Completable
}