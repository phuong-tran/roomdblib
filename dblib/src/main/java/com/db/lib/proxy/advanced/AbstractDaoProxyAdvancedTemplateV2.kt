package com.db.lib.proxy.advanced

import com.db.lib.dao.template.BaseDaoWithLookupAndConverterEntityTemplate
import com.db.lib.proxy.Config

@Suppress("unused")
abstract class AbstractDaoProxyAdvancedTemplateV2<ID, E, A>(
    daoTemplate: BaseDaoWithLookupAndConverterEntityTemplate<ID, E, A>,
    config: Config = Config.defaultConfig
) : AbstractDaoProxyAdvanced<ID, E, A>(
    daoTemplate.entityConverter,
    daoTemplate.entityFinder,
    daoTemplate,
    daoTemplate,
    daoTemplate,
    config
)