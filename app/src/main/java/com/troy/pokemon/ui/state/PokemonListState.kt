package com.troy.pokemon.ui.state

import com.troy.pokemon.ui.data.GroupedPokemon
import com.troy.pokemon.ui.data.Pokemon

sealed class PokemonListState {
    data class OnPokemonListChanged(val groupedPokemonList: List<GroupedPokemon>, val myPokemonList: List<Pokemon>): PokemonListState()
}




