package com.troy.pokemon.data.di

import android.content.Context
import androidx.room.Room
import com.troy.pokemon.data.db.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    private const val DB_NAME = "pokemon_database"

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): PokemonDatabase {
        return Room.databaseBuilder(context, PokemonDatabase::class.java, DB_NAME)
            .build()
    }
}