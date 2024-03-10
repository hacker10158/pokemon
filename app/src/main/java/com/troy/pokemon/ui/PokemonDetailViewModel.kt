package com.troy.pokemon.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.troy.pokemon.domain.GetPokemonStreamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonStreamUseCase: GetPokemonStreamUseCase
): ViewModel() {
    private val _state = MutableStateFlow(PokemonDetailState(null))
    val state: StateFlow<PokemonDetailState> = _state

    fun observePokemon(id: Int) {
        viewModelScope.launch {
            getPokemonStreamUseCase(id).collect {
                _state.emit(PokemonDetailState(it))
            }
        }
    }
}