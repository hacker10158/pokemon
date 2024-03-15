package com.troy.pokemon

import com.troy.pokemon.data.firstToUpperCase
import org.junit.Assert.*
import org.junit.Test

class ExtensionTest {
    @Test
    fun testFirstToUpperCase() {
        assertEquals("", "".firstToUpperCase())
        assertEquals(" ", " ".firstToUpperCase())
        assertEquals("Charizard", "charizard".firstToUpperCase())
        assertEquals("This is a test", "this is a test".firstToUpperCase())
    }
}