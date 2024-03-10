package com.troy.pokemon.data.repo

import com.troy.pokemon.data.db.InfoEntity
import com.troy.pokemon.data.db.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getAllPokemonInfo(limit: Int): List<InfoEntity>

    suspend fun getPokemonByName(name: String): PokemonEntity

    suspend fun getPokemonSpecies(id: Int): PokemonEntity

    fun getPokemonStream(id: Int): Flow<PokemonEntity>

    fun getAllPokemonStream(): Flow<List<PokemonEntity>>
}