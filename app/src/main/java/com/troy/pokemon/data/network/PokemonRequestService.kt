package com.troy.pokemon.data.network

import com.troy.pokemon.data.network.response.GetAllPokemonResponse
import com.troy.pokemon.data.network.response.GetPokemonResponse
import com.troy.pokemon.data.network.response.GetPokemonSpeciesResponse
import retrofit2.http.*

interface PokemonRequestService {

    @GET("api/v2/pokemon")
    suspend fun getAllPokemon(@Query("limit") limit: String): GetAllPokemonResponse

    //this path can be id or name
    @GET("api/v2/pokemon/{path}/")
    suspend fun getPokemon(@Path("path") path: String): GetPokemonResponse

    //this path can be id or name
    @GET("api/v2/pokemon-species/{path}/")
    suspend fun getPokemonSpecies(@Path("path") path: String): GetPokemonSpeciesResponse
}