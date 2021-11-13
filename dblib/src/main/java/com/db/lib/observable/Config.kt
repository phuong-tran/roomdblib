package com.db.lib.observable

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

class Config private constructor(
    val replay: Int,
    val extraBufferCapacity: Int,
    val onBufferOverflow: BufferOverflow
) {

    data class Builder(
        var replay: Int = 0,
        var extraBufferCapacity: Int = 0,
        var onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND
    ) {

        fun replay(replay: Int) = apply { this.replay = replay }

        fun extraBufferCapacity(extraBufferCapacity: Int) =
            apply { this.extraBufferCapacity = extraBufferCapacity }

        fun onBufferOverflow(onBufferOverflow: BufferOverflow) =
            apply { this.onBufferOverflow = onBufferOverflow }

        fun build() = Config(replay, extraBufferCapacity, onBufferOverflow)
    }

    companion object {
        val defaultConfig =
            Builder().replay(0).extraBufferCapacity(0).onBufferOverflow(BufferOverflow.SUSPEND)
                .build()

        fun <A> Config.toMutableSharedFlow(): MutableSharedFlow<A> {
            return MutableSharedFlow(replay, extraBufferCapacity, onBufferOverflow)
        }
    }
}

