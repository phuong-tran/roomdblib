package com.db.lib.dao.template

import com.db.lib.dao.BaseDaoDeleteTemplate
import com.db.lib.dao.BaseDaoInsertTemplate
import com.db.lib.dao.BaseDaoUpdateTemplate

interface BaseDaoTemplate<ID, E> :
    BaseDaoInsertTemplate<ID, E>,
    BaseDaoUpdateTemplate<ID, E>,
    BaseDaoDeleteTemplate<E>
