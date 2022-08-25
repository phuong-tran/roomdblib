package com.db.lib.entity
import kotlinx.coroutines.flow.Flow

interface LookupEntity<ID, E> {
    suspend fun findById(id: ID): E?
    suspend fun findWhereIdIn(ids: List<ID>): List<E>
    fun findWhereIdInFlow(ids: List<ID>): Flow<List<E>>
    fun findByIdFlow(id: ID): Flow<E?>
    suspend fun findAll(): List<E> {
        TODO("Not yet implemented")
    }
    fun findAllFlow(): Flow<List<E>> {
        TODO("Not yet implemented")
    }
}