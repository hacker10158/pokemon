package com.troy.pokemon.data.repo

import com.troy.pokemon.data.PokemonUtil
import com.troy.pokemon.data.db.InfoEntity
import com.troy.pokemon.data.db.PokemonDatabase
import com.troy.pokemon.data.network.PokemonRequestService
import com.troy.pokemon.ui.data.PokemonSpecies
import com.troy.pokemon.data.db.PokemonEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDatabase: PokemonDatabase,
    private val pokemonService: PokemonRequestService
): PokemonRepository {
    override suspend fun getAllPokemonInfo(limit: Int): List<InfoEntity> {
        val list = pokemonDatabase.infoDao().getAllInfo()
        return if (list.size == limit) list
        else {
            pokemonService.getAllPokemon(limit.toString()).results.map {
                val item = InfoEntity(it.name)
                pokemonDatabase.infoDao().insert(item)
                item
            }.toList()
        }
    }

    override fun getAllPokemonStream(): Flow<List<PokemonEntity>> {
        return pokemonDatabase.pokemonDao().getAllPokemon()
    }

    override suspend fun getPokemonByName(name: String): PokemonEntity {
        pokemonDatabase.pokemonDao().getPokemonByName(name)?.let {
            return it
        }
        return pokemonService.getPokemon(name).let {
            val pokemonEntity = PokemonEntity(
                it.id,
                it.name,
                it.sprites.other.officialArtwork.frontDefault,
                PokemonUtil.convertTypesBeanToString(it.types)
            )
            pokemonDatabase.pokemonDao().insert(pokemonEntity)
            pokemonEntity
        }
    }

    override fun getPokemonStream(id: Int): Flow<PokemonEntity> {
        return pokemonDatabase.pokemonDao().getPokemon(id)
    }

    override suspend fun getPokemonSpecies(id: String): PokemonSpecies {
        TODO("Not yet implemented")
    }
}

