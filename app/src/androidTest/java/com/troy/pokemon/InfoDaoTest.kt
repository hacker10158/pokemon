package com.troy.pokemon

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.troy.pokemon.data.db.InfoDao
import com.troy.pokemon.data.db.InfoEntity
import com.troy.pokemon.data.db.PokemonDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class InfoDaoTest {

    private lateinit var infoDao: InfoDao
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
        infoDao = pokemonDatabase.infoDao()
    }

    private var info1 = InfoEntity("1")
    private var info2 = InfoEntity("2")

    private suspend fun addOneItemToDb() {
        infoDao.insert(info1)
    }

    private suspend fun addTwoItemToDb() {
        infoDao.insert(info1)
        infoDao.insert(info2)
    }

    @Test
    @Throws(Exception::class)
    fun testDaoInsert() = runBlocking {
        addOneItemToDb()
        val allInfo = infoDao.getAllInfo()
        assertEquals(info1, allInfo[0])
    }

    @Test
    @Throws(Exception::class)
    fun testDaoGetAllInfo() = runBlocking {
        addTwoItemToDb()
        val allInfo = infoDao.getAllInfo()
        assertEquals(info1, allInfo[0])
        assertEquals(info2, allInfo[1])
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        pokemonDatabase.close()
    }
}