package com.troy.pokemon.totalsolution.network.response

import com.troy.pokemon.totalsolution.network.beans.SpritesBean
import com.troy.pokemon.totalsolution.network.beans.TypesBean

data class GetPokemonResponse(
    val id: Int,
    val name: String,
    val sprites: SpritesBean,
    val types:List<TypesBean>
)