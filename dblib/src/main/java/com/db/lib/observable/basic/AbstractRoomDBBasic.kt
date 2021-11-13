package com.db.lib.observable.basic

import com.db.lib.dml.EntityDeleteTemplate
import com.db.lib.dml.EntityInsertTemplate
import com.db.lib.dml.LookupEntity
import com.db.lib.dml.EntityUpdateTemplate
import com.db.lib.converter.NoopConverter
import com.db.lib.observable.Config
import com.db.lib.observable.advanced.AbstractRoomDBAdvanced

abstract class AbstractRoomDBBasic<ID, E>(
    lookupEntity: LookupEntity<ID, E>,
    entityInsertTemplate: EntityInsertTemplate<ID, E>,
    entityUpdateTemplate: EntityUpdateTemplate<E>,
    entityDeleteTemplate: EntityDeleteTemplate<E>,
    config: Config = Config.defaultConfig
) : AbstractRoomDBAdvanced<ID, E, E>(
    NoopConverter(),
    lookupEntity,
    entityInsertTemplate,
    entityUpdateTemplate,
    entityDeleteTemplate,
    config
)