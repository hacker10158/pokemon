package com.troy.pokemon.usecase

import com.troy.pokemon.data.repo.PokemonRepository
import com.troy.pokemon.domain.GetAllPokemonStreamUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetAllPokemonSteamUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()
    private val repository = mockk<PokemonRepository>()
    private val getAllPokemonStreamUseCase = GetAllPokemonStreamUseCase(repository, testDispatcher)

    @Test
    fun testGetAllPokemonStreamUseCase() = runTest(testDispatcher) {
        coEvery { repository.getAllPokemonStream()} returns flowOf(ArrayList())

        getAllPokemonStreamUseCase()

        coVerify { repository.getAllPokemonStream() }
    }
}