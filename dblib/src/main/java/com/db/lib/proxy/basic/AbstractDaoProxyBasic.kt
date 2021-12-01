package com.db.lib.proxy.basic

import com.db.lib.dml.EntityDeleteTemplate
import com.db.lib.dml.EntityInsertTemplate
import com.db.lib.entity.LookupEntity
import com.db.lib.dml.EntityUpdateTemplate
import com.db.lib.converter.NoopConverter
import com.db.lib.proxy.Config
import com.db.lib.proxy.advanced.AbstractDaoProxyAdvanced

// No Converter
abstract class AbstractDaoProxyBasic<ID, E>(
    lookupEntity: LookupEntity<ID, E>,
    entityInsertTemplate: EntityInsertTemplate<ID, E>,
    entityUpdateTemplate: EntityUpdateTemplate<E>,
    entityDeleteTemplate: EntityDeleteTemplate<E>,
    config: Config = Config.defaultConfig
) : AbstractDaoProxyAdvanced<ID, E, E>(
    NoopConverter(),
    lookupEntity,
    entityInsertTemplate,
    entityUpdateTemplate,
    entityDeleteTemplate,
    config
)