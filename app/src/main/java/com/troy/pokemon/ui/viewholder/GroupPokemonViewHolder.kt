package com.troy.pokemon.ui.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.troy.pokemon.data.firstToUpperCase
import com.troy.pokemon.databinding.ViewHolderGroupPokemonBinding
import com.troy.pokemon.ui.adapter.PokemonAdapter
import com.troy.pokemon.ui.data.GroupedPokemon

class GroupPokemonViewHolder(
    private val binding: ViewHolderGroupPokemonBinding,
    private val onClick: (viewId: Int, pokemonId: Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    private val adapter = PokemonAdapter().apply {
        this.onClickCallback = onClick
    }

    fun bindViewHolder(groupedPokemon: GroupedPokemon) {
        binding.tvTitle.text = groupedPokemon.type.firstToUpperCase()
        binding.tvCount.text = groupedPokemon.pokemonList.size.toString()
        binding.rvPokemon.layoutManager = LinearLayoutManager(binding.root.context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.rvPokemon.adapter = adapter
        adapter.updatePokemonList(groupedPokemon.pokemonList)
    }
}