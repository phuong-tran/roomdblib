package mvi.v1.base

sealed class LoaderState {
    object Loading : LoaderState()
    object Done : LoaderState()
}