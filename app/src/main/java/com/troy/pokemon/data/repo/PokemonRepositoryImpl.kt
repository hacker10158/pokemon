package com.troy.pokemon.data.repo

import com.troy.pokemon.data.PokemonUtil
import com.troy.pokemon.data.db.Info
import com.troy.pokemon.data.db.PokemonDatabase
import com.troy.pokemon.data.network.PokemonRequestService
import com.troy.pokemon.ui.data.PokemonSpecies
import com.troy.pokemon.data.db.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDatabase: PokemonDatabase,
    private val pokemonService: PokemonRequestService
): PokemonRepository {
    override suspend fun getAllPokemonInfo(limit: Int): List<Info> {
        val list = pokemonDatabase.infoDao().getAllInfo()
        return if (list.size == limit) list
        else {
            pokemonService.getAllPokemon(limit.toString()).results.map {
                val item = Info(it.name)
                pokemonDatabase.infoDao().insert(item)
                item
            }.toList()
        }
    }

    override fun getAllPokemonStream(): Flow<List<Pokemon>> {
        return pokemonDatabase.pokemonDao().getAllPokemon()
    }

    override suspend fun getPokemonByName(name: String): Pokemon {
        pokemonDatabase.pokemonDao().getPokemonByName(name)?.let {
            return it
        }
        return pokemonService.getPokemon(name).let {
            val pokemon = Pokemon(
                it.id,
                it.name,
                it.sprites.other.officialArtwork.frontDefault,
                PokemonUtil.convertTypesBeanToString(it.types)
            )
            pokemonDatabase.pokemonDao().insert(pokemon)
            pokemon
        }
    }

    override suspend fun getPokemonSpecies(id: String): PokemonSpecies {
        TODO("Not yet implemented")
    }
}

