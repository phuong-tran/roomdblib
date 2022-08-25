package com.db.lib.entity

import kotlinx.coroutines.flow.Flow

interface LookupEntity<ID, E> {
    suspend fun findById(id: ID): E? {
        TODO("Not yet implemented")
    }

    suspend fun findWhereIdIn(ids: List<ID>): List<E> {
        TODO("Not yet implemented")
    }

    fun findWhereIdInFlow(ids: List<ID>): Flow<List<E>> {
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