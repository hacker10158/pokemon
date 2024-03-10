package com.troy.pokemon.domain

import com.troy.pokemon.data.repo.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchAllPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    private val LIMIT = 151

    suspend operator fun invoke() {
        withContext(Dispatchers.IO) {
            pokemonRepository.getAllPokemonInfo(LIMIT).map {
                async {
                    pokemonRepository.getPokemonByName(it.name)
                }
            }.also { deferredList ->
                deferredList.map {
                    async {
                        pokemonRepository.getPokemonSpecies(it.await().id)
                    }
                }
            }
        }
    }
}