package com.troy.pokemon.data

import java.util.Locale

fun String.firstToUpperCase(): String {
    return replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()}
}