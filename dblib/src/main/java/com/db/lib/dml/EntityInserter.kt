package com.db.lib.dml

interface EntityInserter<ID, E>  {
    suspend fun insert(entity: E): ID
    suspend fun insert(entities: List<E>): Array<ID>
    suspend fun insert(vararg entities: E): Array<ID>
}