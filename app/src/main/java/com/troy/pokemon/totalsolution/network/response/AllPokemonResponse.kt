package com.troy.pokemon.totalsolution.network.response

import com.google.gson.annotations.SerializedName
import com.troy.pokemon.totalsolution.network.beans.SimplePokemonInfo

data class AllPokemonResponse(
    val results: List<SimplePokemonInfo>
)