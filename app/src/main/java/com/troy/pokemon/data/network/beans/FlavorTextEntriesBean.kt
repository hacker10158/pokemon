package com.troy.pokemon.data.network.beans

import com.google.gson.annotations.SerializedName

data class FlavorTextEntriesBean(
    @SerializedName("flavor_text")
    val flavorText:String
)
