package com.troy.pokemon.ui.state

sealed class MainEvent {
    data class ShowPokemonDetail(val id: Int) : MainEvent()
    data class OnError(val throwable: Throwable) : MainEvent()
}