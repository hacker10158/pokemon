package com.troy.pokemon.data.repo

import com.troy.pokemon.ui.data.PokemonSpecies
import com.troy.pokemon.ui.data.Pokemon

interface PokemonRepository {
    suspend fun getAllPokemonName(limit: String): List<String>

    suspend fun getPokemon(id: String): Pokemon

    suspend fun getPokemonSpecies(id: String): PokemonSpecies
}