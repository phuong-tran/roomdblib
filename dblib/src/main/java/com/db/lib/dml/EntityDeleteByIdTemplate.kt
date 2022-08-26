package com.db.lib.dml

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface EntityDeleteByIdTemplate<ID, A> {
    suspend fun deleteById(id: ID): A? {
        TODO("Not yet implemented")
    }

    suspend fun deleteByIds(vararg ids: ID): Collection<A> {
        TODO("Not yet implemented")
    }

    suspend fun deleteByIds(ids: List<ID>): Collection<A> {
        TODO("Not yet implemented")
    }

    fun deleteByIdFlow(id: ID): Flow<A?> {
        TODO("Not yet implemented")
    }

    fun deleteByIdsFlow(vararg ids: ID): Flow<Collection<A>> {
        TODO("Not yet implemented")
    }

    fun deleteByIdsFlow(ids: List<ID>): Flow<Collection<A>> {
        TODO("Not yet implemented")
    }

    fun deleteByIdSingle(id: ID): Single<A> {
        TODO("Not yet implemented")
    }

    fun deleteByIdsSingle(vararg ids: ID): Single<Collection<A>> {
        TODO("Not yet implemented")
    }

    fun deleteByIdsSingle(ids: List<ID>): Single<Collection<A>> {
        TODO("Not yet implemented")
    }

    fun deleteByIdMayBe(id: ID): Maybe<A> {
        TODO("Not yet implemented")
    }

    fun deleteByIdsMaybe(vararg ids: ID): Maybe<Collection<A>> {
        TODO("Not yet implemented")
    }

    fun deleteByIdsMayBe(ids: List<ID>): Maybe<Collection<A>> {
        TODO("Not yet implemented")
    }
}