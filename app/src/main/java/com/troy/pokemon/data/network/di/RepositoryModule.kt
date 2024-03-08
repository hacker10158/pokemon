package com.troy.pokemon.data.network.di

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
    fun provideLauncherRepository(pokemonRequestService: PokemonRequestService): PokemonRepository = PokemonRepositoryImpl(pokemonRequestService)
}