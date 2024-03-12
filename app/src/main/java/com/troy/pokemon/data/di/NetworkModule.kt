package com.troy.pokemon.data.di

import com.troy.pokemon.data.network.PokemonClient
import com.troy.pokemon.data.network.PokemonRequestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providePokemonClient(): PokemonClient = PokemonClient()

    @Singleton
    @Provides
    fun providePoke(pokemonClient: PokemonClient): PokemonRequestService = pokemonClient.service
}