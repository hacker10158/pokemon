package com.troy.pokemon.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.troy.pokemon.domain.CapturePokemonUseCase
import com.troy.pokemon.domain.FetchAllPokemonUseCase
import com.troy.pokemon.domain.GetAllPokemonStreamUseCase
import com.troy.pokemon.domain.GetGroupedPokemonUseCase
import com.troy.pokemon.domain.GetMyPokemonStreamUseCase
import com.troy.pokemon.domain.ReleasePokemonUseCase
import com.troy.pokemon.ui.state.PokemonListEvent
import com.troy.pokemon.ui.state.PokemonListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
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
): BaseViewModel() {
    private val _event = MutableSharedFlow<PokemonListEvent>()
    val event: SharedFlow<PokemonListEvent> = _event
    private val _state = MutableStateFlow<PokemonListState>(PokemonListState.OnPokemonListChanged(ArrayList()))
    val state: StateFlow<PokemonListState> = _state
    private lateinit var job : Job

    init {
        observePokemonDatabase()
        observeMyPokemon()
    }

    private fun observePokemonDatabase() {
        viewModelScope.launch(exceptionHandler) {
            getAllPokemonStreamUseCase().collect {
                _state.emit(PokemonListState.OnPokemonListChanged(getGroupedPokemonUseCase(it)))
            }
        }
    }

    private fun observeMyPokemon() {
        viewModelScope.launch(exceptionHandler) {
            getMyPokemonStreamUseCase().collect {
                _state.emit(PokemonListState.OnMyPokemonChanged(it))
            }
        }
    }

    fun fetchAllPokemon() {
        job = Job()
        viewModelScope.launch(job + exceptionHandler) {
            fetchAllPokemonUseCase()
        }
    }

    fun stopFetch() {
        job.cancel()
    }

    fun capturePokemon(pokeId: Int) {
        viewModelScope.launch(exceptionHandler){
            capturePokemonUseCase(pokeId)
        }
    }

    fun releasePokemon(uid:Int) {
        viewModelScope.launch(exceptionHandler) {
            releasePokemonUseCase(uid)
        }
    }

    override fun handleException(throwable: Throwable) {
        viewModelScope.launch(exceptionHandler) {
            _event.emit(PokemonListEvent.OnError(throwable))
        }
    }
}