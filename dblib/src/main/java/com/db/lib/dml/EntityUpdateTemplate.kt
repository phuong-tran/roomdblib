package com.db.lib.dml

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single


interface EntityUpdateTemplate<E> {
    suspend fun update(entity: E): Int
    suspend fun update(vararg entities: E): Int
    suspend fun update(entities: List<E>): Int

    // RX
    fun updateCompletable(entity: E): Completable
    fun updateCompletable(vararg entities: E): Completable
    fun updateCompletable(entities: List<E>): Completable

    fun updateMaybe(entity: E): Maybe<Int>
    fun updateMaybe(vararg entities: E): Maybe<Int>
    fun updateMaybe(entities: List<E>): Maybe<Int>

    fun updateSingle(entity: E): Single<Int>
    fun updateSingle(vararg entities: E): Single<Int>
    fun updateSingle(entities: List<E>): Single<Int>

}