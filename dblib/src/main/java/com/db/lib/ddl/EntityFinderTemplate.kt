package com.db.lib.ddl

import com.db.lib.entity.LookupEntity
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface EntityFinderTemplate<ID, E> : LookupEntity<ID, E> {
    // RX :
    fun findAllSingle(): Single<List<E>> {
        TODO("Not yet implemented")
    }

    fun findAllMayBe(): Maybe<List<E>> {
        TODO("Not yet implemented")
    }

    fun findByIdSingle(id: ID): Single<E> {
        TODO("Not yet implemented")
    }

    fun findByIdMayBe(id: ID): Maybe<E> {
        TODO("Not yet implemented")
    }

    fun findWhereIdInSingle(ids: List<ID>): Single<List<E>> {
        TODO("Not yet implemented")
    }

    fun findWhereIdInMayBe(ids: List<ID>): Maybe<List<E>> {
        TODO("Not yet implemented")
    }

}