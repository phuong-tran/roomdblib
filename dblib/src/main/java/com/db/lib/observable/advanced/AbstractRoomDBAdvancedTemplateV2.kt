package com.db.lib.observable.advanced

import com.db.lib.dao.template.BaseDaoWithLookupAndConverterEntityTemplate
import com.db.lib.observable.Config

abstract class AbstractRoomDBAdvancedTemplateV2<ID, E, A>(
    daoTemplate: BaseDaoWithLookupAndConverterEntityTemplate<ID, E, A>,
    config: Config = Config.defaultConfig
) : AbstractRoomDBAdvanced<ID, E, A>(
    daoTemplate.entityConverter,
    daoTemplate.lookupEntity,
    daoTemplate,
    daoTemplate,
    daoTemplate,
    config
)