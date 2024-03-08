package com.troy.pokemon.data.network

import com.troy.pokemon.data.network.response.GetAllPokemonResponse
import com.troy.pokemon.data.network.response.GetPokemonResponse
import com.troy.pokemon.data.network.response.GetPokemonSpeciesResponse
import retrofit2.http.*

interface PokemonRequestService {

    @GET("api/v2/pokemon")
    suspend fun getAllPokemon(@Query("limit") limit: String): GetAllPokemonResponse

    @GET("api/v2/pokemon/{id}/")
    suspend fun getPokemon(@Path("id") id: String): GetPokemonResponse

    @GET("api/v2/pokemon-species/{id}/")
    suspend fun getPokemonSpecies(@Path("id") id: String): GetPokemonSpeciesResponse
}