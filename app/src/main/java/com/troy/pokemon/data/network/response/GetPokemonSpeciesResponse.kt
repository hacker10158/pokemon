package com.troy.pokemon.data.network.response

import com.google.gson.annotations.SerializedName
import com.troy.pokemon.data.network.beans.EvolvesFromSpeciesBean
import com.troy.pokemon.data.network.beans.FlavorTextEntriesBean

data class GetPokemonSpeciesResponse(
    val id: Int,
    val name: String,
    @SerializedName("evolves_from_species")
    val evolvesFromSpecies: EvolvesFromSpeciesBean?,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntriesBean>
)