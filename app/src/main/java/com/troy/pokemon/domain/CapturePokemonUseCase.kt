package com.troy.pokemon.domain

import com.troy.pokemon.data.di.IoDispatcher
import com.troy.pokemon.data.repo.PokemonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CapturePokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(id: Int) {
        withContext(dispatcher) {
            pokemonRepository.addMyPokemon(id)
        }
    }
}