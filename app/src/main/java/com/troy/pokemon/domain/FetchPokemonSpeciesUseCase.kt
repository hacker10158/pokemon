package com.troy.pokemon.domain

import com.troy.pokemon.data.repo.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchPokemonSpeciesUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(id: Int) {
        withContext(Dispatchers.IO) {
            pokemonRepository.getPokemonSpecies(id)
        }
    }
}