package com.troy.pokemon.domain

import com.troy.pokemon.data.repo.PokemonRepository
import javax.inject.Inject

class ReleasePokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    suspend operator fun invoke(uid: Int) {
        pokemonRepository.removeMyPokemon(uid)
    }
}