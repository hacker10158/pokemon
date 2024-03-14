package com.troy.pokemon.data.repo

import com.troy.pokemon.ui.data.Info
import com.troy.pokemon.ui.data.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getAllPokemonInfo(limit: Int): List<Info>

    suspend fun getPokemonByName(name: String): Pokemon

    suspend fun getPokemonSpecies(id: Int): Pokemon

    suspend fun addMyPokemon(id: Int)

    suspend fun removeMyPokemon(uid: Int)

    fun getPokemonStream(id: Int): Flow<Pokemon>

    fun getAllPokemonStream(): Flow<List<Pokemon>>

    fun getAllMyPokemonStream(): Flow<List<Pokemon>>
}