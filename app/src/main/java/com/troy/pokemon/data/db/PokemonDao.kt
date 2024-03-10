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
    suspend fun insert(pokemonEntity: PokemonEntity)

    @Update
    suspend fun update(pokemonEntity: PokemonEntity)

    @Delete
    suspend fun delete(pokemonEntity: PokemonEntity)

    @Query("SELECT * from pokemon WHERE id = :id")
    fun getPokemon(id: Int): Flow<PokemonEntity>

    @Query("SELECT * from pokemon WHERE name = :name")
    suspend fun getPokemonByName(name: String): PokemonEntity?

    @Query("SELECT * from pokemon")
    fun getAllPokemon(): Flow<List<PokemonEntity>>
}
