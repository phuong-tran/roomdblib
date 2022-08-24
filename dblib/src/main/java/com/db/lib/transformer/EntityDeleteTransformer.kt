package com.db.lib.transformer

import com.db.lib.dml.EntityDeleteAllTemplate
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface EntityDeleteTransformer<E, A> : EntityDeleteAllTemplate {
    suspend fun delete(entity: E): A
    suspend fun delete(vararg entities: E) : Collection<A>
    suspend fun delete(entities: List<E>) : Collection<A>

    fun deleteFlow(entity: E): Flow<A>
    fun deleteFlow(vararg entities: E) : Flow<Collection<A>>
    fun deleteFlow(entities: List<E>) : Flow<Collection<A>>

    fun deleteSingle(entity: E) : Single<A>
    fun deleteSingle(vararg entities: E) : Single<Collection<A>>
    fun deleteSingle(entities: List<E>) : Single<Collection<A>>

    fun deleteMayBe(entity: E) : Maybe<A>
    fun deleteMaybe(vararg entities: E) : Maybe<Collection<A>>
    fun deleteMayBe(entities: List<E>) : Maybe<Collection<A>>
}