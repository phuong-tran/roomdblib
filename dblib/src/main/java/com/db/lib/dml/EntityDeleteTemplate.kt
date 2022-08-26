package com.db.lib.dml

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface EntityDeleteTemplate<ID, E> : EntityDeleteAllTemplate {
    suspend fun delete(entity: E) : Int
    suspend fun delete(vararg entities: E) : Int
    suspend fun delete(entities: List<E>) : Int

    fun deleteCompletable(entity: E) : Completable
    fun deleteCompletable(vararg entities: E) : Completable
    fun deleteCompletable(entities: List<E>) : Completable

    fun deleteMaybe(entity: E) : Maybe<Int>
    fun deleteMaybe(vararg entities: E) : Maybe<Int>
    fun deleteMaybe(entities: List<E>) : Maybe<Int>

    fun deleteSingle(entity: E) : Single<Int>
    fun deleteSingle(vararg entities: E) : Single<Int>
    fun deleteSingle(entities: List<E>) : Single<Int>
}