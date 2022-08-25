package com.db.lib.dao

import androidx.room.Dao
import com.db.lib.dao.template.BaseDaoTemplate
import com.db.lib.ddl.EntityFinderTemplate

@Dao
interface BaseDao<ID, E> : BaseDaoTemplate<ID, E>, EntityFinderTemplate<ID, E>