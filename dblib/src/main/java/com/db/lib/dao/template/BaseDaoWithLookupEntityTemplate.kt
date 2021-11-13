package com.db.lib.dao.template

import com.db.lib.dml.LookupEntity

interface BaseDaoWithLookupEntityTemplate<ID, E> : BaseDaoTemplate<ID, E> {
    val lookupEntity: LookupEntity<ID, E>
}
