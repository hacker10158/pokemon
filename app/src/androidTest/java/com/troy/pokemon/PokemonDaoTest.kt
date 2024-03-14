package com.troy.pokemon

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.troy.pokemon.data.db.PokemonDao
import com.troy.pokemon.data.db.PokemonDatabase
import com.troy.pokemon.data.db.PokemonEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PokemonDaoTest {

    private lateinit var pokemonDao: PokemonDao
    private lateinit var pokemonDatabase: PokemonDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        pokemonDatabase = Room.inMemoryDatabaseBuilder(context, PokemonDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        pokemonDao = pokemonDatabase.pokemonDao()
    }

    private var pokemon1 = PokemonEntity(1,"bulbasaur","","")
    private var pokemon2 = PokemonEntity(2,"ivysaur","","")

    private suspend fun addOneItemToDb() {
        pokemonDao.insert(pokemon1)
    }

    private suspend fun addTwoItemToDb() {
        pokemonDao.insert(pokemon1)
        pokemonDao.insert(pokemon2)
    }

    @Test
    @Throws(Exception::class)
    fun testDaoInsert() = runBlocking {
        addOneItemToDb()
        val allPokemon = pokemonDao.getAllPokemon().first()
        assertEquals(pokemon1, allPokemon[0])
    }

    @Test
    @Throws(Exception::class)
    fun testDaoUpdate() = runBlocking {
        addOneItemToDb()
        pokemon1.flavorText = "new context"
        pokemonDao.update(pokemon1)
        val allPokemon = pokemonDao.getAllPokemon().first()
        assertEquals("new context", allPokemon[0].flavorText)
    }

    @Test
    @Throws(Exception::class)
    fun testDaoGetPokemon() = runBlocking {
        addTwoItemToDb()
        val pokemon = pokemonDao.getPokemon(pokemon1.id).first()
        assertEquals(pokemon1, pokemon)
    }

    @Test
    @Throws(Exception::class)
    fun testDaoGetPokemonByName() = runBlocking {
        addTwoItemToDb()
        val pokemon = pokemonDao.getPokemonByName(pokemon1.name)
        assertEquals(pokemon1, pokemon)
    }

    @Test
    @Throws(Exception::class)
    fun testDaoGetAllPokemon() = runBlocking {
        addTwoItemToDb()
        val allPokemon = pokemonDao.getAllPokemon().first()
        assertEquals(2, allPokemon.size)
        assertEquals(pokemon1, allPokemon[0])
        assertEquals(pokemon2, allPokemon[1])
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        pokemonDatabase.close()
    }
}