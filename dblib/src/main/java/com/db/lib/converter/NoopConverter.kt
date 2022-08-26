package com.db.lib.converter

class NoopConverter<E> : EntityConverter<E, E> {
    override fun convert(from: E): E = from
    override fun convert(from: Collection<E>): Collection<E> = from
}