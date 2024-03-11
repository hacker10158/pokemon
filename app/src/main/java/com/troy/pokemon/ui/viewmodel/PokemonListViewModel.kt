package com.troy.pokemon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.troy.pokemon.domain.CapturePokemonUseCase
import com.troy.pokemon.domain.FetchAllPokemonUseCase
import com.troy.pokemon.domain.GetAllPokemonStreamUseCase
import com.troy.pokemon.domain.GetGroupedPokemonUseCase
import com.troy.pokemon.domain.GetMyPokemonStreamUseCase
import com.troy.pokemon.domain.ReleasePokemonUseCase
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
    private val getGroupedPokemonUseCase: GetGroupedPokemonUseCase,
    private val getMyPokemonStreamUseCase: GetMyPokemonStreamUseCase,
    private val capturePokemonUseCase: CapturePokemonUseCase,
    private val releasePokemonUseCase: ReleasePokemonUseCase
): ViewModel() {
    private val _state = MutableStateFlow<PokemonListState>(PokemonListState.OnPokemonListChanged(ArrayList()))
    val state: StateFlow<PokemonListState> = _state
    private lateinit var job : Job

    init {
        observePokemonDatabase()
        observeMyPokemon()
    }

    private fun observePokemonDatabase() {
        viewModelScope.launch {
            getAllPokemonStreamUseCase().collect {
                _state.emit(PokemonListState.OnPokemonListChanged(getGroupedPokemonUseCase(it)))
            }
        }
    }

    private fun observeMyPokemon() {
        viewModelScope.launch {
            getMyPokemonStreamUseCase().collect {
                _state.emit(PokemonListState.OnMyPokemonChanged(it))
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

    fun capturePokemon(pokeId: Int) {
        viewModelScope.launch {
            capturePokemonUseCase(pokeId)
        }
    }

    fun releasePokemon(uid:Int) {
        viewModelScope.launch {
            releasePokemonUseCase(uid)
        }
    }
}