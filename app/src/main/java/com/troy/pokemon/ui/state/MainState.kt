package com.troy.pokemon.ui.state

sealed class MainState {
    data class ShowPokemonDetail(val id: Int) : MainState()
}


