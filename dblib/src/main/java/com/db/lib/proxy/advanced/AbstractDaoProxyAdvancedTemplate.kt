package com.db.lib.proxy.advanced

import com.db.lib.converter.EntityConverter
import com.db.lib.dao.template.BaseDaoWithLookupEntityTemplate
import com.db.lib.proxy.Config

abstract class AbstractDaoProxyAdvancedTemplate<ID, E, A>(
    entityConverter: EntityConverter<E, A>,
    daoTemplate: BaseDaoWithLookupEntityTemplate<ID, E>,
    config: Config = Config.defaultConfig
) : AbstractDaoProxyAdvanced<ID, E, A>(
    entityConverter,
    daoTemplate.lookupEntity,
    daoTemplate,
    daoTemplate,
    daoTemplate,
    config
)