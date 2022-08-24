package com.db.lib.ddl

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface EntityFinderTemplate<ID, E> {
    suspend fun findById(id: ID): E?
    fun findByIdAsAsFlow(id: ID): Flow<E?>
    suspend fun findWhereIdIn(ids: List<Long>): List<E>
    fun findWhereIdInAsFlow(ids: List<Long>): Flow<List<E>>
    suspend fun findAll(): List<E>
    fun findAllFlow(): Flow<List<E>>

    // RX :
    fun findAllSingle(): Single<List<E>>
    fun findAllMayBe(): Maybe<List<E>>
    fun findByIdSingle(id: ID): Single<E>
    fun findByIdMayBe(id: ID): Maybe<E>

    fun findWhereIdInSingle(ids: List<Long>): Single<List<E>>
    fun findWhereIdInMayBe(ids: List<Long>): Maybe<List<E>>

}