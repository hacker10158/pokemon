package com.troy.pokemon.ui.state

sealed class PokemonDetailEvent {
    data class OnError(val throwable: Throwable) : PokemonDetailEvent()
}