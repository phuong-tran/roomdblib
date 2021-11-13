package com.db.lib.observable

sealed class RecordsChange<out A> {
    data class RecordInserted<A>(val record: A) : RecordsChange<A>()
    data class RecordsInserted<A>(val records: Collection<A>) : RecordsChange<A>()

    data class RecordUpdated<A>(val record: A) : RecordsChange<A>()
    data class RecordsUpdated<A>(val records: Collection<A> = emptyList()) : RecordsChange<A>()

    data class RecordDeleted<A>(val record: A) : RecordsChange<A>()
    data class RecordsDeleted<A>(val records: Collection<A> = emptyList()) : RecordsChange<A>()

    object EmptyRecord: RecordsChange<Nothing>()
    object RecordsDeletedAll : RecordsChange<Nothing>()
    object UnknownRecordsDeleted : RecordsChange<Nothing>()
    object UnknownRecordsUpdated : RecordsChange<Nothing>()
    object UnknownRecordsInserted : RecordsChange<Nothing>()
    object UnKnowRecordsChanged : RecordsChange<Nothing>()
}