package mvi.v1.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

import mvi.v1.base.IIntent
import mvi.v1.base.IModel
import mvi.v1.base.IState

/*abstract class AbstractMviViewModel<S : IState, I : IIntent> : ViewModel(), IModel<S, I> {

    override val intents: Channel<I> by lazy {
        Channel(Channel.UNLIMITED)
    }

    private val _state = MutableLiveData<S>()
    override val state: LiveData<S>
        get() = _state

    abstract fun onIntent(intent: I)

    private fun handleIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { intent ->
                onIntent(intent)
            }
        }
    }

    protected fun sendIntent(intent: I) {
        viewModelScope.launch {
            intents.send(intent)
        }
    }

    init {
        handleIntent()
    }
}*/


abstract class AbstractMviViewModel<S : IState, I : IIntent> : ViewModel(), IModel<S, I> {

    override val intents: Channel<I> by lazy {
        Channel(Channel.UNLIMITED)
    }

    private val _state = MutableLiveData<S>()
    override val state: LiveData<S>
        get() = _state

    abstract fun onIntent(intent: I)

    private fun handleIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect { intent ->
                onIntent(intent)
            }
        }
    }

    protected fun sendIntent(intent: I) {
        viewModelScope.launch {
            intents.send(intent)
        }
    }

    init {
        handleIntent()
    }
}


