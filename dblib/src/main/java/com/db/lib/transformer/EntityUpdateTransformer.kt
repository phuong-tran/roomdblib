package com.db.lib.transformer

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface EntityUpdateTransformer<E, A> {
    suspend fun update(entity: E): A
    suspend fun update(vararg entities: E): Collection<A>
    suspend fun update(entities: List<E>): Collection<A>

    fun updateFlow(entity: E): Flow<A>
    fun updateFlow(vararg entities: E): Flow<Collection<A>>
    fun updateFlow(entities: List<E>): Flow<Collection<A>>

    fun updateSingle(entity: E): Single<A>
    fun updateSingle(entities: List<E>): Single<Collection<A>>
    fun updateSingle(vararg entities: E): Single<Collection<A>>

    fun updateMaybe(entity: E): Maybe<A>
    fun updateMaybe(entities: List<E>): Maybe<Collection<A>>
    fun updateMaybe(vararg entities: E): Maybe<Collection<A>>
}