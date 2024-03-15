package com.troy.pokemon.usecase

import com.troy.pokemon.domain.GetGroupedPokemonUseCase
import com.troy.pokemon.ui.data.Pokemon
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetGroupedPokemonUseCaseTest {
    private val getGroupedPokemonUseCase = GetGroupedPokemonUseCase()
    private lateinit var mockPokemonList : ArrayList<Pokemon>
    private val type1 = "type1"
    private val type2 = "type2"
    private val type3 = "type3"

    @Before
    fun initMockData() {
        mockPokemonList = ArrayList()
        mockPokemonList.add(Pokemon(0,"","", listOf(type1)))
        mockPokemonList.add(Pokemon(1,"","", listOf(type2)))
        mockPokemonList.add(Pokemon(2,"","", listOf(type1, type2)))
        mockPokemonList.add(Pokemon(3,"","", listOf(type1, type3)))
    }

    @Test
    fun testGetGroupedPokemonUseCase() {
        val result = getGroupedPokemonUseCase(mockPokemonList)
        val group1 = result.find {
            it.type == type1
        }
        val group2 = result.find {
            it.type == type2
        }
        val group3 = result.find {
            it.type == type3
        }

        assertEquals(3, result.size)
        assertEquals(3, group1?.pokemonList?.size)
        assertEquals(2, group2?.pokemonList?.size)
        assertEquals(1, group3?.pokemonList?.size)
    }

    @Test
    fun testEmptyCase() {
        val result = getGroupedPokemonUseCase(ArrayList())

        assertEquals(0, result.size)
    }
}