package com.troy.pokemon.data.network.response

import com.troy.pokemon.data.network.beans.SpritesBean
import com.troy.pokemon.data.network.beans.TypesBean

data class GetPokemonResponse(
    val id: Int,
    val name: String,
    val sprites: SpritesBean,
    val types:List<TypesBean>
)