package com.db.lib.proxy.basic

import com.db.lib.converter.NoopConverter
import com.db.lib.dao.template.BaseDaoWithLookupEntityTemplate
import com.db.lib.proxy.Config
import com.db.lib.proxy.advanced.AbstractDaoProxyAdvancedTemplate

abstract class AbstractDaoProxyBasicTemplate<ID, E>(
    daoTemplate: BaseDaoWithLookupEntityTemplate<ID, E>,
    config: Config = Config.defaultConfig
) : AbstractDaoProxyAdvancedTemplate<ID, E, E>(NoopConverter(), daoTemplate, config)
