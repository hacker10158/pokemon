package com.troy.pokemon.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pokemon: Pokemon)

    @Update
    suspend fun update(pokemon: Pokemon)

    @Delete
    suspend fun delete(pokemon: Pokemon)

    @Query("SELECT * from pokemon WHERE id = :id")
    suspend fun getPokemon(id: Int): Pokemon?

    @Query("SELECT * from pokemon WHERE name = :name")
    suspend fun getPokemonByName(name: String): Pokemon?

    @Query("SELECT * from pokemon")
    fun getAllPokemon(): Flow<List<Pokemon>>
}
