package com.troy.pokemon

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.troy.pokemon.data.db.MyPokemonDao
import com.troy.pokemon.data.db.MyPokemonEntity
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
class MyPokemonDaoTest {

    private lateinit var myPokemonDao: MyPokemonDao
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
        myPokemonDao = pokemonDatabase.myPokemonDao()
        pokemonDao = pokemonDatabase.pokemonDao()
    }

    private var myPokemon1 = MyPokemonEntity(0,1, 0L)
    private var myPokemon2 = MyPokemonEntity(1, 1,1000L)
    private var pokemon = PokemonEntity(1,"","","")

    //add data to relation db
    private suspend fun addItemToPokemonDao() {
        pokemonDao.insert(pokemon)
    }

    private suspend fun addOneItemToDb() {
        myPokemonDao.insert(myPokemon1)
    }

    private suspend fun addTwoItemToDb() {
        myPokemonDao.insert(myPokemon1)
        myPokemonDao.insert(myPokemon2)
    }

    private suspend fun deleteOneItemToDb() {
        myPokemonDao.delete(myPokemon1.uid!!)
    }

    @Test
    @Throws(Exception::class)
    fun testDaoInsert() = runBlocking {
        addItemToPokemonDao()
        addOneItemToDb()
        val allMyPokemon = myPokemonDao.getAllMyPokemon().first()
        assertEquals(myPokemon1, allMyPokemon[0].myPokemon)
        assertEquals(pokemon, allMyPokemon[0].pokemon)
    }

    @Test
    @Throws(Exception::class)
    fun testDaoGetMyPokemon() = runBlocking {
        addItemToPokemonDao()
        addTwoItemToDb()
        val allMyPokemon = myPokemonDao.getAllMyPokemon().first()
        assertEquals(2, allMyPokemon.size)
        assertEquals(myPokemon2, allMyPokemon[0].myPokemon)
        assertEquals(pokemon, allMyPokemon[0].pokemon)
        assertEquals(myPokemon1, allMyPokemon[1].myPokemon)
        assertEquals(pokemon, allMyPokemon[1].pokemon)
    }

    @Test
    @Throws(Exception::class)
    fun testDaoDelete() = runBlocking {
        addItemToPokemonDao()
        addTwoItemToDb()
        deleteOneItemToDb()
        val allMyPokemon = myPokemonDao.getAllMyPokemon().first()
        assertEquals(1, allMyPokemon.size)
        assertEquals(myPokemon2, allMyPokemon[0].myPokemon)
        assertEquals(pokemon, allMyPokemon[0].pokemon)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        pokemonDatabase.close()
    }
}