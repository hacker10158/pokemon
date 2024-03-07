package com.troy.pokemon.totalsolution.network.beans

import com.google.gson.annotations.SerializedName

data class OfficialArtworkBean (
    @SerializedName("front_default")
    val frontDefault:String
)