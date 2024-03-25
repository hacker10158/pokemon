package com.troy.pokemon.usecase

import com.troy.pokemon.data.repo.PokemonRepository
import com.troy.pokemon.domain.GetPokemonByNameUseCase
import com.troy.pokemon.ui.data.Pokemon
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetPokemonByNameUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()
    private val repository = mockk<PokemonRepository>()
    private val getPokemonByNameUseCase = GetPokemonByNameUseCase(repository, testDispatcher)

    @Test
    fun testGetPokemonByNameUseCase() = runTest(testDispatcher) {
        coEvery { repository.getPokemonByName(any())} returns Pokemon(0,"","",ArrayList())

        getPokemonByNameUseCase("")

        coVerify { repository.getPokemonByName(any()) }
    }
}