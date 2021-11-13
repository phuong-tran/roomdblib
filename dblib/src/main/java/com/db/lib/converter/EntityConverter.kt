package com.db.lib.converter
interface EntityConverter<E, A> {
    fun convert(from: E) : A

    fun convert(from: Collection<E>) : Collection<A> {
        return from.map {
            convert(it)
        }
    }
}