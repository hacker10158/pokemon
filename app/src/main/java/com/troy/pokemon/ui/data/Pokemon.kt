package com.troy.pokemon.ui.data

import com.troy.pokemon.data.PokemonUtil
import com.troy.pokemon.data.db.PokemonEntity

data class Pokemon(
    val id:Int,
    val name:String,
    val imageUrl:String,
    val types: List<String>,
    var evolvesFrom: String? = null,
    var flavorText: String? = null,
    var uid: Int = 0
) {
    companion object {
        fun fromPokemonEntity(entity: PokemonEntity): Pokemon {
            return Pokemon(
                entity.id,
                entity.name,
                entity.imageUrl,
                PokemonUtil.convertStringToTypes( entity.typeString),
                entity.evolvesFrom,
                entity.flavorText
            )
        }
    }
}