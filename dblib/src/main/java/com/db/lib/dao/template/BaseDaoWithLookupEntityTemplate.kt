package com.db.lib.dao.template

import com.db.lib.ddl.EntityFinderTemplate

interface BaseDaoWithLookupEntityTemplate<ID, E> : BaseDaoTemplate<ID, E> {
    val entityFinder: EntityFinderTemplate<ID, E>
}
