package com.troy.pokemon.totalsolution.network

import com.troy.pokemon.totalsolution.network.response.GetAllPokemonResponse
import com.troy.pokemon.totalsolution.network.response.GetPokemonResponse
import com.troy.pokemon.totalsolution.network.response.GetPokemonSpeciesResponse
import retrofit2.http.*

interface PokemonRequestService {

    @GET("api/v2/pokemon")
    suspend fun getAllPokemon(@Query("limit") limit: String): GetAllPokemonResponse

    @GET("api/v2/pokemon/{id}/")
    suspend fun getPokemon(@Path("id") id: String): GetPokemonResponse

    @GET("api/v2/pokemon-species/{id}/")
    suspend fun getPokemonSpecies(@Path("id") id: String): GetPokemonSpeciesResponse
}