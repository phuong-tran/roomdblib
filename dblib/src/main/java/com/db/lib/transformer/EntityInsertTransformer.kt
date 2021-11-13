package com.db.lib.transformer

import kotlinx.coroutines.flow.Flow

interface EntityInsertTransformer<E, A>  {
    suspend fun insert(entity: E): A
    suspend fun insert(entities: List<E>): Collection<A>
    suspend fun insert(vararg entities: E): Collection<A>

    fun insertAsFlow(entity: E): Flow<A>
    fun insertAsFlow(entities: List<E>): Flow<Collection<A>>
    fun insertAsFlow(vararg entities: E): Flow<Collection<A>>
}