package com.troy.pokemon.usecase

import com.troy.pokemon.data.repo.PokemonRepository
import com.troy.pokemon.domain.GetMyPokemonStreamUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetMyPokemonSteamUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()
    private val repository = mockk<PokemonRepository>()
    private val getMyPokemonStreamUseCase = GetMyPokemonStreamUseCase(repository, testDispatcher)

    @Test
    fun testGetMyPokemonStreamUseCase() = runTest(testDispatcher) {
        coEvery { repository.getAllMyPokemonStream()} returns flowOf(ArrayList())

        getMyPokemonStreamUseCase()

        coVerify { repository.getAllMyPokemonStream() }
    }
}