package com.troy.pokemon.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.troy.pokemon.domain.FetchPokemonUseCase
import com.troy.pokemon.domain.GetAllPokemonStreamUseCase
import com.troy.pokemon.domain.GetGroupedPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchPokemonUseCase: FetchPokemonUseCase,
    private val getAllPokemonStreamUseCase: GetAllPokemonStreamUseCase,
    private val getGroupedPokemonUseCase: GetGroupedPokemonUseCase
): ViewModel() {
    private val _state = MutableStateFlow(MainState(ArrayList()))
    val state: StateFlow<MainState> = _state

    // TODO provide flow for observe captured pokemon

    init {
        observePokemonDatabase()
    }

    private fun observePokemonDatabase() {
        viewModelScope.launch {
            getAllPokemonStreamUseCase().collect {
                _state.emit(MainState(getGroupedPokemonUseCase(it)))
            }
        }
    }

    fun fetchAllPokemon() {
        viewModelScope.launch {
            fetchPokemonUseCase()
        }
    }

    fun capturePokemon() {
        //TODO
    }

    fun releasePokemon() {
        //TODO
    }

}