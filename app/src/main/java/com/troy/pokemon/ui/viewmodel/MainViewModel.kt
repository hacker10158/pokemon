package com.troy.pokemon.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.troy.pokemon.ui.state.MainEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel() {
    private val _event = MutableSharedFlow<MainEvent>()
    val event: SharedFlow<MainEvent> = _event

    fun showPokemonDetail(id:Int) {
        viewModelScope.launch(exceptionHandler) {
            _event.emit(MainEvent.ShowPokemonDetail(id))
        }
    }

    override fun handleException(throwable: Throwable) {
        viewModelScope.launch(exceptionHandler) {
            _event.emit(MainEvent.OnError(throwable))
        }
    }
}