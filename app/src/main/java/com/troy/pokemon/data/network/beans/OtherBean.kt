package com.troy.pokemon.data.network.beans

import com.google.gson.annotations.SerializedName

data class OtherBean(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkBean
)
