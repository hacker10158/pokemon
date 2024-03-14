package com.troy.pokemon.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imageUrl: String,
    val typeString: String, // type will convert to string : fire,fly...etc
    var evolvesFrom: String? = null,
    var flavorText: String? = null
)