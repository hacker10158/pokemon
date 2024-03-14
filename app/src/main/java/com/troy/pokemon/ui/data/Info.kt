package com.troy.pokemon.ui.data

import com.troy.pokemon.data.db.InfoEntity

data class Info(
    val name: String
) {
    companion object {
        fun fromInfoEntity(entity: InfoEntity): Info {
            return Info(entity.name)
        }
    }
}