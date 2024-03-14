package com.troy.pokemon.domain

import com.troy.pokemon.data.repo.PokemonRepository
import com.troy.pokemon.ui.data.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPokemonStreamUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    operator fun invoke(): Flow<List<Pokemon>> = pokemonRepository.getAllPokemonStream()
}