package com.troy.pokemon.ui.state

sealed class PokemonListEvent {
    data class OnError(val throwable: Throwable) : PokemonListEvent()
}