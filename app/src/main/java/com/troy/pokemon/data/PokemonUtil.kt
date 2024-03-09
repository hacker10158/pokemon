package com.troy.pokemon.data

import com.troy.pokemon.data.network.beans.TypesBean

object PokemonUtil {
    private const val SPLIT_SYMBOL = ","

    fun convertTypesBeanToString(types: List<TypesBean>): String {
        val sb = StringBuilder()

        for (i in types.indices) {
            sb.append(types[i].type.name)
            if(i != types.lastIndex )
                sb.append(SPLIT_SYMBOL)
        }

        return sb.toString()
    }

    fun convertStringToTypes(typeString: String): List<String> {
        return typeString.split(SPLIT_SYMBOL)
    }
}