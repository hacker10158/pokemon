package com.troy.pokemon.data.repo

import com.troy.pokemon.data.PokemonUtil
import com.troy.pokemon.data.db.InfoEntity
import com.troy.pokemon.data.db.MyPokemonEntity
import com.troy.pokemon.data.db.PokemonDatabase
import com.troy.pokemon.data.db.PokemonEntity
import com.troy.pokemon.data.network.PokemonRequestService
import com.troy.pokemon.ui.data.Info
import com.troy.pokemon.ui.data.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDatabase: PokemonDatabase,
    private val pokemonService: PokemonRequestService
): PokemonRepository {

    companion object {
        const val LANGUAGE = "en"
    }
    override suspend fun getAllPokemonInfo(limit: Int): List<Info> {
        val list = pokemonDatabase.infoDao().getAllInfo()
        return if (list.size == limit) list.map { Info.fromInfoEntity(it) }
        else {
            pokemonService.getAllPokemon(limit.toString()).results.map {
                val item = InfoEntity(it.name)
                pokemonDatabase.infoDao().insert(item)
                item
            }.toList().map { Info.fromInfoEntity(it) }
        }
    }

    override suspend fun getPokemonByName(name: String): Pokemon {
        pokemonDatabase.pokemonDao().getPokemonByName(name)?.let {
            return Pokemon.fromPokemonEntity(it)
        }
        return pokemonService.getPokemon(name).let {
            val pokemonEntity = PokemonEntity(
                it.id,
                it.name,
                it.sprites.other.officialArtwork.frontDefault,
                PokemonUtil.convertTypesBeanToString(it.types)
            )
            pokemonDatabase.pokemonDao().insert(pokemonEntity)
            Pokemon.fromPokemonEntity(pokemonEntity)
        }
    }

    override suspend fun getPokemonSpecies(id: Int): Pokemon {
        val entity = pokemonDatabase.pokemonDao().getPokemon(id).first()
        if(entity.flavorText == null) {
            pokemonService.getPokemonSpecies(id.toString()).also {
                entity.evolvesFrom = it.evolvesFromSpecies?.name
                entity.flavorText = it.flavorTextEntries.find { flavorTextEntry ->
                    flavorTextEntry.language.name == LANGUAGE
                }?.flavorText
            }
            pokemonDatabase.pokemonDao().update(entity)
        }
        return Pokemon.fromPokemonEntity(entity)
    }

    override suspend fun addMyPokemon(id: Int) {
        pokemonDatabase.myPokemonDao().insert(
            MyPokemonEntity(
                null,
                id = id,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    override suspend fun removeMyPokemon(uid: Int) {
        pokemonDatabase.myPokemonDao().delete(uid)
    }

    override fun getPokemonStream(id: Int): Flow<Pokemon> {
        return pokemonDatabase.pokemonDao().getPokemon(id).map {
            Pokemon.fromPokemonEntity(it)
        }
    }

    override fun getAllPokemonStream(): Flow<List<Pokemon>> {
        return pokemonDatabase.pokemonDao().getAllPokemon().map { list ->
            list.map {
                Pokemon.fromPokemonEntity(it)
            }
        }
    }

    override fun getAllMyPokemonStream(): Flow<List<Pokemon>> {
        return pokemonDatabase.myPokemonDao().getAllMyPokemon().map { list ->
            list.map {
                Pokemon( it.myPokemon.id,
                    it.pokemon.name,
                    it.pokemon.imageUrl,
                    PokemonUtil.convertStringToTypes(it.pokemon.typeString),
                    uid = it.myPokemon.uid?: 0
                )
            }
        }
    }
}