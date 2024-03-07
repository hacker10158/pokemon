package com.troy.pokemon.totalsolution.network.response

import com.troy.pokemon.totalsolution.network.beans.PokemonInfoBean

data class GetAllPokemonResponse(
    val results: List<PokemonInfoBean>
)