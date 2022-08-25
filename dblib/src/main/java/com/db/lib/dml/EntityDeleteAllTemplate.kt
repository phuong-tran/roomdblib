package com.db.lib.dml

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface EntityDeleteAllTemplate {
    suspend fun deleteAll() : Int

    fun deleteAllCompletable(): Completable {
        TODO("Not yet implemented")
    }

    fun deleteAllFlow(): Flow<Int> {
        TODO("Not yet implemented")
    }

    fun deleteAllSingle(): Single<Int> {
        TODO("Not yet implemented")
    }

    fun deleteAllMaybe() : Maybe<Int> {
        TODO("Not yet implemented")
    }
}