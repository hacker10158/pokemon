package com.troy.pokemon.domain

import com.troy.pokemon.data.repo.PokemonRepository
import javax.inject.Inject

class FetchPokemonSpeciesUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int) {
        pokemonRepository.getPokemonSpecies(id)
    }
}