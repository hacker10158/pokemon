package com.troy.pokemon.ui

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.troy.pokemon.databinding.ViewHolderGroupPokemonBinding
import com.troy.pokemon.databinding.ViewHolderPokemonBinding
import com.troy.pokemon.ui.data.GroupedPokemon

import com.troy.pokemon.ui.data.Pokemon
import java.util.Locale

class PokemonViewHolder(
    private val binding: ViewHolderPokemonBinding,
    private val onClickCallback: (viewId: Int, pokemonId: Int) -> Unit
): RecyclerView.ViewHolder(binding.root)  {

    fun bindViewHolder(pokemon: Pokemon) {
        binding.tvName.text = pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()}
        Glide.with(binding.root)
            .load(pokemon.imageUrl)
            .into(binding.ivPokemon)

        binding.ivPokemon.setOnClickListener {
            onClickCallback(it.id, pokemon.id)
        }
    }
}