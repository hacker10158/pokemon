package com.troy.pokemon.data.network.response

import com.troy.pokemon.data.network.beans.PokemonInfoBean

data class GetAllPokemonResponse(
    val results: List<PokemonInfoBean>
)