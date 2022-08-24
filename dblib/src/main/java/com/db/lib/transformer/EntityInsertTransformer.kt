package com.db.lib.transformer

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface EntityInsertTransformer<E, A>  {
    suspend fun insert(entity: E): A
    suspend fun insert(entities: List<E>): Collection<A>
    suspend fun insert(vararg entities: E): Collection<A>

    fun insertFlow(entity: E): Flow<A>
    fun insertFlow(entities: List<E>): Flow<Collection<A>>
    fun insertFlow(vararg entities: E): Flow<Collection<A>>

    fun insertSingle(entity: E): Single<A>
    fun insertSingle(entities: List<E>): Single<Collection<A>>
    fun insertSingle(vararg entities: E): Single<Collection<A>>

    fun insertMaybe(entity: E): Maybe<A>
    fun insertMaybe(entities: List<E>): Maybe<Collection<A>>
    fun insertMaybe(vararg entities: E): Maybe<Collection<A>>

}