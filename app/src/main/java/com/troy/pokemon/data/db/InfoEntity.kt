package com.troy.pokemon.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "info")
data class InfoEntity(
    @PrimaryKey
    val name: String
)