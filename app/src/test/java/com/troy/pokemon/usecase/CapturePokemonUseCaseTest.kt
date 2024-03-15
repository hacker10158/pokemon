package com.troy.pokemon.usecase

import com.troy.pokemon.data.repo.PokemonRepository
import com.troy.pokemon.domain.CapturePokemonUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CapturePokemonUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()
    private val repository = mockk<PokemonRepository>()
    private val capturePokemonUseCase = CapturePokemonUseCase(repository, testDispatcher)

    @Test
    fun testCapturePokemonUseCase() = runTest(testDispatcher) {
        coEvery { repository.addMyPokemon(any()) } returns Unit

        capturePokemonUseCase(0)

        coVerify { repository.addMyPokemon(any()) }
    }
}