package com.troy.pokemon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.troy.pokemon.domain.FetchPokemonSpeciesUseCase
import com.troy.pokemon.domain.GetPokemonStreamUseCase
import com.troy.pokemon.ui.state.PokemonDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonStreamUseCase: GetPokemonStreamUseCase,
    private val fetchPokemonSpeciesUseCase: FetchPokemonSpeciesUseCase
): ViewModel() {
    private val _state = MutableStateFlow(PokemonDetailState(null))
    val state: StateFlow<PokemonDetailState> = _state

    fun observePokemon(id: Int) {
        viewModelScope.launch {
            launch(Dispatchers.IO) {
                fetchPokemonSpeciesUseCase(id)
            }
            getPokemonStreamUseCase(id).collect { pokemon ->
                launch {
                    pokemon.evolvesFrom?.let { name ->
                        _state.emit(PokemonDetailState(pokemon, getPokemonStreamUseCase(name)))
                    }
                }
                _state.emit(PokemonDetailState(pokemon))
            }
        }
    }
}