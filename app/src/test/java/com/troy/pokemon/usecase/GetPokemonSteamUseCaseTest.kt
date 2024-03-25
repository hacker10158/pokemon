package com.troy.pokemon.usecase

import com.troy.pokemon.data.repo.PokemonRepository
import com.troy.pokemon.domain.GetPokemonStreamUseCase
import com.troy.pokemon.ui.data.Pokemon
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetPokemonSteamUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()
    private val repository = mockk<PokemonRepository>()
    private val getPokemonStreamUseCase = GetPokemonStreamUseCase(repository, testDispatcher)

    @Test
    fun testGetPokemonStreamUseCase() = runTest(testDispatcher) {
        coEvery { repository.getPokemonStream(any())} returns flowOf(Pokemon(0,"","",ArrayList()))

        getPokemonStreamUseCase(0)

        coVerify { repository.getPokemonStream(any()) }
    }
}