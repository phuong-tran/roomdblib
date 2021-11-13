package com.db.lib.dml
import kotlinx.coroutines.flow.Flow

interface LookupEntity<ID, E> {
    suspend fun fetchById(id: ID): E?
    suspend fun fetchWhereIdIn(ids: List<ID>): List<E>
    fun fetchWhereIdInAsFlow(ids: List<ID>): Flow<List<E>>
    fun fetchByIdAsAsFlow(id: ID): Flow<E?>
}