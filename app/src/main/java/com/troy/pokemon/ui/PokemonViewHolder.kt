package com.troy.pokemon.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.troy.pokemon.data.firstToUpperCase
import com.troy.pokemon.databinding.ViewHolderPokemonBinding
import com.troy.pokemon.ui.data.Pokemon

class PokemonViewHolder(
    private val binding: ViewHolderPokemonBinding,
    private val onClickCallback: (viewId: Int, pokemonId: Int) -> Unit
): RecyclerView.ViewHolder(binding.root)  {

    fun bindViewHolder(pokemon: Pokemon) {
        binding.tvName.text = pokemon.name.firstToUpperCase()
        Glide.with(binding.root)
            .load(pokemon.imageUrl)
            .into(binding.ivPokemon)

        binding.ivPokemon.setOnClickListener {
            onClickCallback(it.id, pokemon.id)
        }
    }
}