package com.troy.pokemon.totalsolution.network

import com.troy.pokemon.totalsolution.network.response.AllPokemonResponse
import retrofit2.http.*

interface PokemonRequestService {

    @GET("api/v2/pokemon")
    suspend fun getAllPokemon(@Query("limit") limit: String): AllPokemonResponse

}