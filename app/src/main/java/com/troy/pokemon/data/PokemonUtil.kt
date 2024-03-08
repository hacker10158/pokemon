package com.troy.pokemon.data

import com.troy.pokemon.data.network.beans.TypesBean

object PokemonUtil {
    fun convertTypesBeanToString(types: List<TypesBean>): String {
        val sb = StringBuilder()

        types.forEach {
            sb.append(it.type.name)
            sb.append(",")
        }
        return sb.toString()
    }
}