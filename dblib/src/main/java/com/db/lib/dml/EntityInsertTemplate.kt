package com.db.lib.dml

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single


interface EntityInsertTemplate<ID, E>  {
    suspend fun insert(entity: E): ID
    suspend fun insert(entities: List<E>): Array<ID>
    suspend fun insert(vararg entities: E): Array<ID>

    // RX
    fun insertCompletable(entity: E): Completable
    fun insertCompletable(entities: List<E>): Completable
    fun insertCompletable(vararg entities: E): Completable

    fun insertMaybe(entity: E): Maybe<ID>
    fun insertMaybe(entities: List<E>): Maybe<List<ID>>
    fun insertMaybe(vararg entities: E): Maybe<List<ID>>


    fun insertSingle(entity: E): Single<ID>
    fun insertSingle(entities: List<E>): Single<List<ID>>
    fun insertSingle(vararg entities: E): Single<List<ID>>
}