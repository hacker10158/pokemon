package com.troy.pokemon.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.troy.pokemon.databinding.ViewHolderPokemonBinding
import com.troy.pokemon.ui.data.Pokemon

class PokemonAdapter: RecyclerView.Adapter<PokemonViewHolder>() {
    private var pokemonList = ArrayList<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(ViewHolderPokemonBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bindViewHolder(pokemonList[position])
    }

    fun updatePokemonList(list: List<Pokemon>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return pokemonList[oldItemPosition].id == list[newItemPosition].id
            }

            override fun getOldListSize(): Int {
                return pokemonList.size
            }

            override fun getNewListSize(): Int {
                return list.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return pokemonList[oldItemPosition].name == list[newItemPosition].name
            }
        })

        pokemonList.clear()
        pokemonList.addAll(list)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}