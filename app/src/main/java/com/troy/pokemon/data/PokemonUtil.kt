package com.troy.pokemon.data

import com.troy.pokemon.data.network.beans.TypesBean

object PokemonUtil {
    private const val SPLIT_SYMBOL = ","

    fun convertTypesBeanToString(types: List<TypesBean>): String {
        return types.joinToString(SPLIT_SYMBOL) { it.type.name }
    }

    fun convertStringToTypes(typeString: String): List<String> {
        return typeString.split(SPLIT_SYMBOL)
    }
}