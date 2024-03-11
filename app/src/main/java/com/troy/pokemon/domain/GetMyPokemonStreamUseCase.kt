package com.troy.pokemon.domain

import com.troy.pokemon.data.PokemonUtil
import com.troy.pokemon.data.repo.PokemonRepository
import com.troy.pokemon.ui.data.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMyPokemonStreamUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    operator fun invoke(): Flow<List<Pokemon>> {
        return pokemonRepository.getAllMyPokemonStream().map { list ->
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