package com.troy.pokemon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.troy.pokemon.domain.FetchAllPokemonUseCase
import com.troy.pokemon.domain.GetAllPokemonStreamUseCase
import com.troy.pokemon.domain.GetGroupedPokemonUseCase
import com.troy.pokemon.ui.state.PokemonListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val fetchAllPokemonUseCase: FetchAllPokemonUseCase,
    private val getAllPokemonStreamUseCase: GetAllPokemonStreamUseCase,
    private val getGroupedPokemonUseCase: GetGroupedPokemonUseCase
): ViewModel() {
    private val _state = MutableStateFlow(PokemonListState(ArrayList()))
    val state: StateFlow<PokemonListState> = _state
    private lateinit var job : Job
    // TODO provide flow for observe captured pokemon

    init {
        observePokemonDatabase()
    }

    private fun observePokemonDatabase() {
        viewModelScope.launch {
            getAllPokemonStreamUseCase().collect {
                _state.emit(PokemonListState(getGroupedPokemonUseCase(it)))
            }
        }
    }

    fun fetchAllPokemon() {
        job = Job()
        viewModelScope.launch(job) {
            fetchAllPokemonUseCase()
        }
    }

    fun stopFetch() {
        job.cancel()
    }

    fun capturePokemon() {
        //TODO
    }

    fun releasePokemon() {
        //TODO
    }

}