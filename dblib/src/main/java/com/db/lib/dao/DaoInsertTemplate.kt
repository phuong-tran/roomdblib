package com.db.lib.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.db.lib.dml.EntityInsertTemplate
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface DaoInsertTemplate<ID, E> : EntityInsertTemplate<ID, E> {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(entity: E): ID

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(entities: List<E>): Array<ID>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(vararg entities: E): Array<ID>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertCompletable(entity: E): Completable

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertCompletable(entities: List<E>): Completable

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertCompletable(vararg entities: E): Completable

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertMaybe(entity: E): Maybe<ID>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertMaybe(entities: List<E>): Maybe<List<ID>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertMaybe(vararg entities: E): Maybe<List<ID>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertSingle(entity: E): Single<ID>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertSingle(entities: List<E>): Single<List<ID>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertSingle(vararg entities: E): Single<List<ID>>
}
