package com.troy.pokemon.data.network.beans

data class PokemonBean(
    val id: Int,
    val name: String,
    val sprites: SpritesBean,
    val types:List<TypesBean>
)
