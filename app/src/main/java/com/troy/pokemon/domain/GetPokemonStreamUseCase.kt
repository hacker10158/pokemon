package com.troy.pokemon.domain

import com.troy.pokemon.data.PokemonUtil
import com.troy.pokemon.data.repo.PokemonRepository
import com.troy.pokemon.ui.data.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPokemonStreamUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    operator fun invoke(id:Int): Flow<Pokemon> {
        return pokemonRepository.getPokemonStream(id).map {
            Pokemon(
                it.id,
                it.name,
                it.imageUrl,
                PokemonUtil.convertStringToTypes( it.typeString)
            )
        }
    }
}