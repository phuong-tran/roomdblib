package com.db.lib.observable.basic

import com.db.lib.dml.EntityDeleter
import com.db.lib.dml.EntityInserter
import com.db.lib.dml.LookupEntity
import com.db.lib.dml.EntityUpdater
import com.db.lib.converter.NoopConverter
import com.db.lib.observable.Config
import com.db.lib.observable.advanced.AbstractRoomDBAdvanced

abstract class AbstractRoomDBBasic<ID, E>(
    lookupEntity: LookupEntity<ID, E>,
    entityInserter: EntityInserter<ID, E>,
    entityUpdater: EntityUpdater<E>,
    entityDeleter: EntityDeleter<E>,
    config: Config = Config.defaultConfig
) : AbstractRoomDBAdvanced<ID, E, E>(
    NoopConverter(),
    lookupEntity,
    entityInserter,
    entityUpdater,
    entityDeleter,
    config
)