package com.troy.pokemon.data.di

import com.troy.pokemon.data.db.PokemonDatabase
import com.troy.pokemon.data.network.PokemonRequestService
import com.troy.pokemon.data.repo.PokemonRepository
import com.troy.pokemon.data.repo.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideLauncherRepository(
        pokemonDatabase: PokemonDatabase,
        pokemonRequestService: PokemonRequestService
    ): PokemonRepository =
        PokemonRepositoryImpl(pokemonDatabase, pokemonRequestService)
}