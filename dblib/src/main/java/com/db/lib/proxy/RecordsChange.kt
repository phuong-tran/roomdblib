package com.db.lib.proxy

sealed class RecordsChange<out A> {
    data class RecordInserted<A>(val record: A) : RecordsChange<A>()
    data class RecordsInserted<A>(val records: Collection<A>) : RecordsChange<A>()

    data class RecordUpdated<A>(val record: A) : RecordsChange<A>()
    data class RecordsUpdated<A>(val records: Collection<A> = emptyList()) : RecordsChange<A>()

    data class RecordDeleted<A>(val record: A) : RecordsChange<A>()
    data class RecordsDeleted<A>(val records: Collection<A> = emptyList()) : RecordsChange<A>()

    @Suppress("unused")
    object RecordChangeDisabled: RecordsChange<Nothing>()
    @Suppress("unused")
    object EmptyRecord: RecordsChange<Nothing>()
    @Suppress("unused")
    object RecordsDeletedAll : RecordsChange<Nothing>()
    @Suppress("unused")
    object UnknownRecordsDeleted : RecordsChange<Nothing>()
    @Suppress("unused")
    object UnknownRecordsUpdated : RecordsChange<Nothing>()
    @Suppress("unused")
    object UnknownRecordsInserted : RecordsChange<Nothing>()
    @Suppress("unused")
    object UnKnowRecordsChanged : RecordsChange<Nothing>()
}