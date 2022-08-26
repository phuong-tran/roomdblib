package com.db.lib.entity

import kotlinx.coroutines.flow.Flow

interface LookupEntity<ID, E> {
    suspend fun findById(id: ID): E? {
        TODO("Not yet implemented")
    }

    suspend fun findByIds(ids: List<ID>): List<E> {
        TODO("Not yet implemented")
    }

    fun findByIdsFlow(ids: List<ID>): Flow<List<E>> {
        TODO("Not yet implemented")
    }

    fun findByIdFlow(id: ID): Flow<E?> {
        TODO("Not yet implemented")
    }

    suspend fun findAll(): List<E> {
        TODO("Not yet implemented")
    }

    fun findAllFlow(): Flow<List<E>> {
        TODO("Not yet implemented")
    }
}