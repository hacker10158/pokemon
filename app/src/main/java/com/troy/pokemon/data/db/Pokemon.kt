package com.troy.pokemon.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey
    val id:Int,
    val name:String,
    val imageUrl:String,
    val types: String // type will convert to string : fire,fly...etc
)
