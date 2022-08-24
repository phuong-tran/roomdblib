package com.db.lib.dao.template

import com.db.lib.converter.EntityConverter
import com.db.lib.ddl.EntityFinderTemplate

interface BaseDaoWithLookupAndConverterEntityTemplate<ID, E, A> : BaseDaoTemplate<ID, E> {
    val entityFinder: EntityFinderTemplate<ID, E>
    val entityConverter: EntityConverter<E, A>
}
