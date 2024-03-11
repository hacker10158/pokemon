package com.troy.pokemon.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_pokemon")
data class MyPokemonEntity(
    @PrimaryKey(autoGenerate = true)
    var uid:Int? = 0,
    val id:Int,
    val timestamp: Long
)
