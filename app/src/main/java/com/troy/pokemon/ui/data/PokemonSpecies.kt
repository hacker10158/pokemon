package com.troy.pokemon.ui.data

import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    val id: Int,
    @SerializedName("evolves_from_species")
    val evolvesFromSpecies: String,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: String
)
