package com.troy.pokemon.domain

import com.troy.pokemon.ui.data.GroupedPokemon
import com.troy.pokemon.ui.data.Pokemon
import javax.inject.Inject

class GetGroupedPokemonUseCase @Inject constructor() {
    operator fun invoke(pokemonList: List<Pokemon>): List<GroupedPokemon> {
        val groupedPokemon = ArrayList<GroupedPokemon>()
        val map = HashMap<String, ArrayList<Pokemon>>()

        pokemonList.forEach { pokemon ->
            pokemon.types.forEach { type ->
                val list = map[type]?: ArrayList()
                list.add(pokemon)
                map[type] = list
            }
        }

        map.forEach { entry ->
            groupedPokemon.add(
                GroupedPokemon(
                    entry.key,
                    entry.value.sortedBy { it.id }
                )
            )
        }

        groupedPokemon.sortBy { it.type }

        return groupedPokemon
    }
}