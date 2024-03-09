package com.troy.pokemon

import com.troy.pokemon.data.PokemonUtil
import com.troy.pokemon.data.network.beans.TypeBean
import com.troy.pokemon.data.network.beans.TypesBean
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class PokemonUtilTest {
    private lateinit var case1 : ArrayList<TypesBean>
    private lateinit var case2 : ArrayList<TypesBean>

    @Before
    fun setUp() {
        case1 = ArrayList()
        case1.add(TypesBean(0, TypeBean("fire","")))

        case2 = ArrayList()
        case2.add(TypesBean(0, TypeBean("water","")))
        case2.add(TypesBean(1, TypeBean("fly","")))
    }

    @Test
    fun testConvertTypesBeanToString() {
        assertEquals("fire", PokemonUtil.convertTypesBeanToString(case1))
        assertEquals("water,fly", PokemonUtil.convertTypesBeanToString(case2))
    }

    @Test
    fun testConvertStringToTypes() {
        val result1 = PokemonUtil.convertStringToTypes("fire")
        assertEquals("fire", result1[0])
        assertEquals(1, result1.size)

        val result2 = PokemonUtil.convertStringToTypes("water,fly")
        assertEquals("water", result2[0])
        assertEquals("fly", result2[1])
        assertEquals(2, result2.size)
    }
}