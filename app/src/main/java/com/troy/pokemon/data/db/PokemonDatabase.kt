package com.troy.pokemon.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [InfoEntity::class, PokemonEntity::class,  MyPokemonEntity::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun infoDao(): InfoDao
    abstract fun pokemonDao(): PokemonDao
    abstract fun myPokemonDao(): MyPokemonDao
}