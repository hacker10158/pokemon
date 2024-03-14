package com.troy.pokemon.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyPokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pokemonEntity: MyPokemonEntity)

    @Query("DELETE FROM my_pokemon WHERE uid = :uid")
    suspend fun delete(uid: Int)

    @Query("SELECT * from my_pokemon ORDER BY timestamp DESC")
    fun getAllMyPokemon(): Flow<List<MyPokeWithData>>
}