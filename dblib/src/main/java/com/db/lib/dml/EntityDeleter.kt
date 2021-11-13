package com.db.lib.dml

interface EntityDeleter<E> {
    suspend fun delete(entity: E)
    suspend fun delete(vararg entities: E)
    suspend fun delete(entities: List<E>)
}