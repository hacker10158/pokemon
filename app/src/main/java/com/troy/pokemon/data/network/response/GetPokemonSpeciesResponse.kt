package com.troy.pokemon.data.network.response

import com.google.gson.annotations.SerializedName
import com.troy.pokemon.data.network.beans.EvolvesFromSpeciseBean
import com.troy.pokemon.data.network.beans.FlavorTextEntriesBean

data class GetPokemonSpeciesResponse(
    val id: Int,
    val name: String,
    @SerializedName("evolves_from_species")
    val evolvesFromSpecies: EvolvesFromSpeciseBean?,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntriesBean>
)