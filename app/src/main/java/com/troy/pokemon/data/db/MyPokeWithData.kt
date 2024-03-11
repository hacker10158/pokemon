package com.troy.pokemon.data.db

import androidx.room.Embedded
import androidx.room.Relation

data class MyPokeWithData(
    @Embedded var myPokemon: MyPokemonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    var pokemon: PokemonEntity
)