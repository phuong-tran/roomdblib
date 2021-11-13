package com.db.lib.dao.template

import com.db.lib.entity.LookupEntity

interface BaseDaoWithLookupEntityTemplate<ID, E> : BaseDaoTemplate<ID, E> {
    val lookupEntity: LookupEntity<ID, E>
}
