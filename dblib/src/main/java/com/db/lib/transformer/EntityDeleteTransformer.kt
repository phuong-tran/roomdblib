package com.db.lib.transformer

import kotlinx.coroutines.flow.Flow

interface EntityDeleteTransformer<E, A> {
    suspend fun delete(entity: E): A
    suspend fun delete(vararg entities: E) : Collection<A>
    suspend fun delete(entities: List<E>) : Collection<A>

    fun deleteAsFlow(entity: E): Flow<A>
    fun deleteAsFlow(vararg entities: E) : Flow<Collection<A>>
    fun deleteAsFlow(entities: List<E>) : Flow<Collection<A>>
}