package com.db.lib.dao.template

import com.db.lib.dao.DaoDeleteTemplate
import com.db.lib.dao.DaoInsertTemplate
import com.db.lib.dao.DaoUpdateTemplate

interface BaseDaoTemplate<ID, E> :
    DaoInsertTemplate<ID, E>,
    DaoUpdateTemplate<ID, E>,
    DaoDeleteTemplate<E>