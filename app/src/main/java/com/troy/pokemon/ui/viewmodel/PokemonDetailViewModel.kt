package com.troy.pokemon.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.troy.pokemon.domain.FetchPokemonSpeciesUseCase
import com.troy.pokemon.domain.GetPokemonStreamUseCase
import com.troy.pokemon.ui.state.PokemonDetailEvent
import com.troy.pokemon.ui.state.PokemonDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonStreamUseCase: GetPokemonStreamUseCase,
    private val fetchPokemonSpeciesUseCase: FetchPokemonSpeciesUseCase
): BaseViewModel() {
    private val _event = MutableSharedFlow<PokemonDetailEvent>()
    val event: SharedFlow<PokemonDetailEvent> = _event
    private val _state = MutableStateFlow(PokemonDetailState(null))
    val state: StateFlow<PokemonDetailState> = _state

    fun observePokemon(id: Int) {
        viewModelScope.launch(exceptionHandler) {
            launch(exceptionHandler) {
                fetchPokemonSpeciesUseCase(id)
            }
            getPokemonStreamUseCase(id).collect { pokemon ->
                launch(exceptionHandler) {
                    pokemon.evolvesFrom?.let { name ->
                        _state.emit(PokemonDetailState(pokemon, getPokemonStreamUseCase(name)))
                    }
                }
                _state.emit(PokemonDetailState(pokemon))
            }
        }
    }

    override fun handleException(throwable: Throwable) {
        viewModelScope.launch(exceptionHandler) {
            _event.emit(PokemonDetailEvent.OnError(throwable))
        }
    }
}