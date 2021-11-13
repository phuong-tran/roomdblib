package com.db.lib.dao.template

import com.db.lib.dao.BaseDaoDeleter
import com.db.lib.dao.BaseDaoInserter
import com.db.lib.dao.BaseDaoUpdater

interface BaseDaoTemplate<ID, E> :
    BaseDaoInserter<ID, E>,
    BaseDaoUpdater<ID, E>,
    BaseDaoDeleter<E>
