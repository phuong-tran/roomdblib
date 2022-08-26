package com.pt.room.lib.db

import com.db.lib.ddl.EntityFinderTemplate
import com.db.lib.proxy.advanced.AbstractDaoProxyAdvanced
import com.pt.room.lib.model.StudentModel

class StudentDaoProxy (
    entityFinder: EntityFinderTemplate<Long, StudentEntity>,
    entityConverter: StudentEntityConverter,
    dao: StudentDao,
) : AbstractDaoProxyAdvanced<Long, StudentEntity, StudentModel>(
    entityConverter = entityConverter,
    entityFinder = entityFinder,
    entityInsertTemplate = dao,
    entityUpdateTemplate = dao,
    entityDeleteTemplate = dao,
)