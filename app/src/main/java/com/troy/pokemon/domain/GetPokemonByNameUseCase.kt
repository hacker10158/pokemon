package com.troy.pokemon.domain

import com.troy.pokemon.data.di.IoDispatcher
import com.troy.pokemon.data.repo.PokemonRepository
import com.troy.pokemon.ui.data.Pokemon
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPokemonByNameUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(name: String): Pokemon = withContext(dispatcher) {
        pokemonRepository.getPokemonByName(name)
    }
}