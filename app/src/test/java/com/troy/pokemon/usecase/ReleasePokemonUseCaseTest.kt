package com.troy.pokemon.usecase

import com.troy.pokemon.data.repo.PokemonRepository
import com.troy.pokemon.domain.ReleasePokemonUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ReleasePokemonUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()
    private val repository = mockk<PokemonRepository>()
    private val releasePokemonUseCase = ReleasePokemonUseCase(repository, testDispatcher)

    @Test
    fun testReleasePokemonUseCase() = runTest(testDispatcher) {
        coEvery { repository.removeMyPokemon(any())} returns Unit

        releasePokemonUseCase(0)

        coVerify { repository.removeMyPokemon(any()) }
    }
}