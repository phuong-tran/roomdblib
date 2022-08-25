package com.db.lib.transformer

import kotlinx.coroutines.flow.Flow

interface EntityUpdateTransformer<E, A> {
    suspend fun update(entity: E): A
    suspend fun update(vararg entities: E): Collection<A>
    suspend fun update(entities: List<E>): Collection<A>

    fun updateFlow(entity: E): Flow<A>
    fun updateFlow(vararg entities: E): Flow<Collection<A>>
    fun updateFlow(entities: List<E>): Flow<Collection<A>>
}