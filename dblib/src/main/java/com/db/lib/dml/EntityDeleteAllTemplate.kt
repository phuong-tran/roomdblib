package com.db.lib.dml

import io.reactivex.rxjava3.core.Completable
import kotlinx.coroutines.flow.Flow

interface EntityDeleteAllTemplate {
    suspend fun deleteAll() : Int
    fun deleteAllCompletable(): Completable
    fun deleteAllFlow(): Flow<Int>
}