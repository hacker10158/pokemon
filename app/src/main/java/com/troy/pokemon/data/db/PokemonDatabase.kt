package com.troy.pokemon.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [InfoEntity::class, PokemonEntity::class,  MyPokemonEntity::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun infoDao(): InfoDao
    abstract fun pokemonDao(): PokemonDao
    abstract fun myPokemonDao(): MyPokemonDao

    companion object {
        @Volatile
        private var Instance: PokemonDatabase? = null

        fun getDatabase(context: Context): PokemonDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, PokemonDatabase::class.java, "pokemon_database")
                    .build().also { Instance = it }
            }
        }
    }
}

