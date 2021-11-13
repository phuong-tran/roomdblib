package com.db.lib.observable.basic

import com.db.lib.converter.NoopConverter
import com.db.lib.dao.template.BaseDaoWithLookupEntityTemplate
import com.db.lib.observable.Config
import com.db.lib.observable.advanced.AbstractRoomDBAdvancedTemplate

abstract class AbstractRoomDBBasicTemplate<ID, E>(
    daoTemplate: BaseDaoWithLookupEntityTemplate<ID, E>,
    config: Config = Config.defaultConfig
) : AbstractRoomDBAdvancedTemplate<ID, E, E>(NoopConverter(), daoTemplate, config)
