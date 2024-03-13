package com.troy.pokemon.ui.viewholder

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.troy.pokemon.data.POKE_ID
import com.troy.pokemon.data.TransitionCache
import com.troy.pokemon.data.UID
import com.troy.pokemon.data.firstToUpperCase
import com.troy.pokemon.databinding.ViewHolderPokemonBinding
import com.troy.pokemon.ui.data.Pokemon

class PokemonViewHolder(
    private val binding: ViewHolderPokemonBinding,
    private val onClickCallback: (viewId: Int, bundle: Bundle) -> Unit
): RecyclerView.ViewHolder(binding.root)  {

    fun bindViewHolder(pokemon: Pokemon) {
        binding.tvName.text = pokemon.name.firstToUpperCase()
        Glide.with(binding.root)
            .load(pokemon.imageUrl)
            .into(binding.ivPokemon)

        binding.ivPokemon.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(POKE_ID, pokemon.id)
            TransitionCache.addTransitionView(it)
            onClickCallback(it.id, bundle)
        }

        binding.ivPokeBall.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(POKE_ID, pokemon.id)
            bundle.putInt(UID, pokemon.uid)
            onClickCallback(it.id, bundle)
        }
    }
}