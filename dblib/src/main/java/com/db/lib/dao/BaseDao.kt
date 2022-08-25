package com.db.lib.dao

import com.db.lib.dao.template.BaseDaoTemplate
import com.db.lib.ddl.EntityFinderTemplate

/**
 * Do not use it for now, wait for dependencies
 * https://issuetracker.google.com/issues/160258066
 */
@Suppress("unused")
interface BaseDao<ID, E> : BaseDaoTemplate<ID, E>, EntityFinderTemplate<ID, E>