package com.db.lib.dao.template

import com.db.lib.converter.EntityConverter
import com.db.lib.entity.LookupEntity

interface BaseDaoWithLookupAndConverterEntityTemplate<ID, E, A> : BaseDaoTemplate<ID, E> {
    val lookupEntity: LookupEntity<ID, E>
    val entityConverter: EntityConverter<E, A>
}
