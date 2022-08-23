package mvi.v1.app

import mvi.v1.base.IIntent


sealed class UserIntent: IIntent {
    object  RefreshUsers : UserIntent()
    object FetchUsers : UserIntent()
}
