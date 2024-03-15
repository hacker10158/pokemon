package com.troy.pokemon.usecase

import com.troy.pokemon.data.repo.PokemonRepository
import com.troy.pokemon.domain.FetchAllPokemonUseCase
import com.troy.pokemon.ui.data.Info
import com.troy.pokemon.ui.data.Pokemon
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class FetchAllPokemonUseCaseTest {
    private val testDispatcher = StandardTestDispatcher()
    private val repository = mockk<PokemonRepository>()
    private val fetchAllPokemonUseCase = FetchAllPokemonUseCase(repository, testDispatcher)
    private val mockPokemon = Pokemon(0,"","",ArrayList())
    private lateinit var mockInfoList: ArrayList<Info>

    @Before
    fun initMockData() {
        mockInfoList = ArrayList()
        mockInfoList.add(Info(""))
        mockInfoList.add(Info(""))
    }

    @Test
    fun testFetchAllPokemonUseCase() = runTest(testDispatcher) {
        coEvery { repository.getAllPokemonInfo(any()) } returns mockInfoList
        coEvery { repository.getPokemonByName(any()) } returns mockPokemon
        coEvery { repository.getPokemonSpecies(any()) } returns mockPokemon

        fetchAllPokemonUseCase()

        coVerify { repository.getAllPokemonInfo(any()) }
        coVerify(exactly = mockInfoList.size) { repository.getPokemonByName(any()) }
        coVerify(exactly = mockInfoList.size) { repository.getPokemonSpecies(any()) }
    }
}