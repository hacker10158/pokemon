package com.troy.pokemon.usecase

import com.troy.pokemon.data.repo.PokemonRepository
import com.troy.pokemon.domain.FetchPokemonSpeciesUseCase
import com.troy.pokemon.ui.data.Pokemon
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FetchPokemonSpeciesUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()
    private val repository = mockk<PokemonRepository>()
    private val fetchPokemonSpeciesUseCase = FetchPokemonSpeciesUseCase(repository, testDispatcher)
    private val mockPokemon = Pokemon(0,"","",ArrayList())

    @Test
    fun testFetchPokemonSpeciesUseCase() = runTest(testDispatcher) {
        coEvery { repository.getPokemonSpecies(any()) } returns mockPokemon

        fetchPokemonSpeciesUseCase(0)

        coVerify { repository.getPokemonSpecies(any()) }
    }
}