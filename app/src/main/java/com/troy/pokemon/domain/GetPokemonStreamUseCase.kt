package com.troy.pokemon.domain

import com.troy.pokemon.data.repo.PokemonRepository
import com.troy.pokemon.ui.data.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonStreamUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    operator fun invoke(id:Int): Flow<Pokemon> = pokemonRepository.getPokemonStream(id)

    suspend operator fun invoke(name: String): Pokemon = pokemonRepository.getPokemonByName(name)
}