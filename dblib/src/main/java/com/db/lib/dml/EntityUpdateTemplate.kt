package com.db.lib.dml

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


interface EntityUpdateTemplate<E> {
    suspend fun update(entity: E): Int
    suspend fun update(vararg entities: E): Int
    suspend fun update(entities: List<E>): Int

    // RX
    fun updateCompletable(entity: E): Completable
    fun updateCompletable(vararg entities: E): Completable
    fun updateCompletable(entities: List<E>): Completable
}