package com.troy.pokemon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.troy.pokemon.ui.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val _clickedItem = MutableSharedFlow<MainState>()
    val clickedItem: SharedFlow<MainState> = _clickedItem

    fun showPokemonDetail(id:Int) {
        viewModelScope.launch {
            _clickedItem.emit(MainState.ShowPokemonDetail(id))
        }
    }
}