package mvi.v1.base.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import mvi.v1.base.IIntent
import mvi.v1.base.IModel
import mvi.v1.base.IState

class NewClass {}


class UserUseCase1() {
    fun fetchUser(): Flow<String> {
        return flow<String> {
            emit("ABC")
        }
    }

    fun fetchUser1(context: Context): Flow<String> {
        return flow<String> {
            emit("ABC")
        }
    }

    fun refreshUser(): Flow<String> {
        return flow<String> {
            emit("ABC")
        }
    }

}

sealed class Actions {
    object FetchUser: Actions()
    object RefreshUser: Actions()
    data class FetchWithId(val id: String) : Actions()
}

class UserViewModel: ViewModel(){
    private val userUseCase = UserUseCase1()

    private val actionChannel: Channel<Actions> = Channel(Channel.UNLIMITED)

    private val shareStateFlow: SharedFlow<String> = actionChannel.receiveAsFlow().flatMapLatest { action ->
        when(action) {
            Actions.FetchUser -> {
                userUseCase.fetchUser()
            }
            Actions.RefreshUser -> {
                userUseCase.fetchUser()
            }
            else -> {
                TODO()
            }
        }
    }.shareIn(viewModelScope, SharingStarted.Eagerly)


    suspend fun sendAction(action: Actions) {
        actionChannel.send(action)
    }

}



