package com.db.lib.dao

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import com.db.lib.dml.EntityDeleteTemplate
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface DaoDeleteTemplate<ID, E> : EntityDeleteTemplate<ID, E> {
    @Transaction
    @Delete
    override suspend fun delete(entity: E): Int

    @Transaction
    @Delete
    override suspend fun delete(vararg entities: E): Int

    @Transaction
    @Delete
    override suspend fun delete(entities: List<E>): Int

    // RX
    @Transaction
    @Delete
    override fun deleteCompletable(entity: E): Completable

    @Transaction
    @Delete
    override fun deleteCompletable(vararg entities: E): Completable

    @Transaction
    @Delete
    override fun deleteCompletable(entities: List<E>): Completable

    @Transaction
    @Delete
    override fun deleteMaybe(entity: E): Maybe<Int>

    @Transaction
    @Delete
    override fun deleteMaybe(vararg entities: E): Maybe<Int>

    @Transaction
    @Delete
    override fun deleteMaybe(entities: List<E>): Maybe<Int>

    @Transaction
    @Delete
    override fun deleteSingle(entity: E): Single<Int>

    @Transaction
    @Delete
    override fun deleteSingle(vararg entities: E): Single<Int>

    @Transaction
    @Delete
    override fun deleteSingle(entities: List<E>): Single<Int>
}
