package com.db.lib.dml

interface EntityUpdateTemplate<E> {
    suspend fun update(entity: E)
    suspend fun update(vararg entities: E)
    suspend fun update(entities: List<E>)
}