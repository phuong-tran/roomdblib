package com.db.lib.observable.advanced

import com.db.lib.converter.EntityConverter
import com.db.lib.dao.template.BaseDaoWithLookupEntityTemplate
import com.db.lib.observable.Config

abstract class AbstractRoomDBAdvancedTemplate<ID, E, A>(
    entityConverter: EntityConverter<E, A>,
    daoTemplate: BaseDaoWithLookupEntityTemplate<ID, E>,
    config: Config = Config.defaultConfig
) : AbstractRoomDBAdvanced<ID, E, A>(
    entityConverter,
    daoTemplate.lookupEntity,
    daoTemplate,
    daoTemplate,
    daoTemplate,
    config
)