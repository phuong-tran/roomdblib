package com.db.lib.proxy.basic

import com.db.lib.converter.NoopConverter
import com.db.lib.ddl.EntityFinderTemplate
import com.db.lib.dml.EntityDeleteTemplate
import com.db.lib.dml.EntityInsertTemplate
import com.db.lib.dml.EntityUpdateTemplate
import com.db.lib.proxy.Config
import com.db.lib.proxy.advanced.AbstractDaoProxyAdvanced

// No Converter
@Suppress("unused")
abstract class AbstractDaoProxyBasic<ID, E>(
    entityFinder: EntityFinderTemplate<ID, E>,
    entityInsertTemplate: EntityInsertTemplate<ID, E>,
    entityUpdateTemplate: EntityUpdateTemplate<E>,
    entityDeleteTemplate: EntityDeleteTemplate<ID, E>,
    config: Config = Config.defaultConfig
) : AbstractDaoProxyAdvanced<ID, E, E>(
    NoopConverter(),
    entityFinder,
    entityInsertTemplate,
    entityUpdateTemplate,
    entityDeleteTemplate,
    config
)