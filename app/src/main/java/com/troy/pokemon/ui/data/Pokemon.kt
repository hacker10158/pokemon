package com.troy.pokemon.ui.data

data class Pokemon(
    val id:Int,
    val name:String,
    val imageUrl:String,
    val types: List<String>,
    var evolvesFrom: String? = null,
    var flavorText: String? = null
)