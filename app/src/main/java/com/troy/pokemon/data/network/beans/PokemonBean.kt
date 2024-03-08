package com.troy.pokemon.data.network.beans

import com.google.gson.annotations.SerializedName

data class PokemonBean(
    val id: Int,
    @SerializedName("flavor_text_entries")
    val types: String
)
