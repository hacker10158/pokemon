package com.troy.pokemon.totalsolution.network.beans

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesBean(
    val id: Int,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: String
)
