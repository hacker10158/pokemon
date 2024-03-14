package com.troy.pokemon.ui.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.troy.pokemon.R
import com.troy.pokemon.databinding.ViewHolderMyPokemonBinding
import com.troy.pokemon.ui.adapter.OnClickCallback
import com.troy.pokemon.ui.adapter.PokemonAdapter
import com.troy.pokemon.ui.data.Pokemon

class MyPokemonViewHolder(
    private val binding: ViewHolderMyPokemonBinding,
    private val onClick: OnClickCallback
): RecyclerView.ViewHolder(binding.root) {
    private val adapter = PokemonAdapter().apply {
        this.onClickCallback = onClick
    }

    fun bindViewHolder(pokemonList: List<Pokemon>) {
        binding.tvTitle.text = binding.root.resources.getString(R.string.my_pocket)
        binding.tvCount.text = pokemonList.size.toString()
        binding.rvPokemon.layoutManager = LinearLayoutManager(binding.root.context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.rvPokemon.adapter = adapter
        adapter.updatePokemonList(pokemonList)
    }
}