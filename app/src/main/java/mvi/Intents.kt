/*
package mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch


sealed class Intent<out T> {

}

sealed class State<out T> {
    object Idle : State<Nothing>()
    object Loading : State<Nothing>()
    data class Success<out T>(val data: T) : State<T>()
    data class Error(val throwable: Throwable) : State<Nothing>()
}


abstract class ViewModelMVI<I, S>(initValue: S) : ViewModel() {
    private val intentChanel = Channel<I>(Channel.UNLIMITED)
    private val mutableStateFlow: MutableStateFlow<S> = MutableStateFlow(initValue)
    val state = mutableStateFlow.asStateFlow()

    init {
        handleIntent()
    }

    protected suspend fun sendIntent(intent: I) {
        intentChanel.send(intent)
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intentChanel.consumeAsFlow().collect { intent ->
                onIntent(intent)
            }
        }
    }

    abstract fun onIntent(intent: I)

    protected fun changeState(state: S) {
        mutableStateFlow.value = state
    }

}
*/
