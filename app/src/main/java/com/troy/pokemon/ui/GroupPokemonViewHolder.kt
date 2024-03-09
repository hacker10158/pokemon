package com.troy.pokemon.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.troy.pokemon.databinding.ViewHolderGroupPokemonBinding
import com.troy.pokemon.ui.data.GroupedPokemon
import java.util.Locale

class GroupPokemonViewHolder(private val binding: ViewHolderGroupPokemonBinding): RecyclerView.ViewHolder(binding.root)  {
    val adapter = PokemonAdapter()

    fun bindViewHolder(groupedPokemon: GroupedPokemon) {
        binding.tvTitle.text = groupedPokemon.type.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()}
        binding.tvCount.text = groupedPokemon.pokemonList.size.toString()
        binding.rvPokemon.layoutManager = LinearLayoutManager(binding.root.context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.rvPokemon.adapter = adapter
        adapter.updatePokemonList(groupedPokemon.pokemonList)
    }
}