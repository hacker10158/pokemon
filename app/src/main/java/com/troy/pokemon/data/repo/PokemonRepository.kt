package com.troy.pokemon.data.repo

import com.troy.pokemon.data.db.Info
import com.troy.pokemon.ui.data.PokemonSpecies
import com.troy.pokemon.data.db.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getAllPokemonInfo(limit: Int): List<Info>

    suspend fun getPokemonByName(name: String): Pokemon

    suspend fun getPokemonSpecies(id: String): PokemonSpecies

    fun getAllPokemonStream(): Flow<List<Pokemon>>
}