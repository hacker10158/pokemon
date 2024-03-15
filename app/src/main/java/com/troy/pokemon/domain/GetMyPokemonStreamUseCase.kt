package com.troy.pokemon.domain

import com.troy.pokemon.data.di.IoDispatcher
import com.troy.pokemon.data.repo.PokemonRepository
import com.troy.pokemon.ui.data.Pokemon
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMyPokemonStreamUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<List<Pokemon>> =
        pokemonRepository.getAllMyPokemonStream().flowOn(dispatcher)
}