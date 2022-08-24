package com.db.lib.transformer

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface EntityDeleteTransformer<E, A> {
    suspend fun delete(entity: E): A
    suspend fun delete(vararg entities: E) : Collection<A>
    suspend fun delete(entities: List<E>) : Collection<A>

    fun deleteAsFlow(entity: E): Flow<A>
    fun deleteAsFlow(vararg entities: E) : Flow<Collection<A>>
    fun deleteAsFlow(entities: List<E>) : Flow<Collection<A>>

    fun deleteAsSingle(entity: E) : Single<A>
    fun deleteAsSingle(vararg entities: E) : Single<Collection<A>>
    fun deleteAsSingle(entities: List<E>) : Single<Collection<A>>

    fun deleteAsMayBe(entity: E) : Maybe<A>
    fun deleteAsMaybe(vararg entities: E) : Maybe<Collection<A>>
    fun deleteAsMayBe(entities: List<E>) : Maybe<Collection<A>>

}