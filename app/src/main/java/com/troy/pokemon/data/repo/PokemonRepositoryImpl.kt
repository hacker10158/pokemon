package com.troy.pokemon.data.repo

import com.troy.pokemon.data.network.PokemonRequestService
import com.troy.pokemon.ui.data.PokemonSpecies
import com.troy.pokemon.ui.data.Pokemon
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val pokemonService: PokemonRequestService): PokemonRepository {
    override suspend fun getAllPokemonName(limit: String): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemon(id: String): Pokemon {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonSpecies(id: String): PokemonSpecies {
        TODO("Not yet implemented")
    }
}

