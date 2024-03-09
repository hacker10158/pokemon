package com.troy.pokemon.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.troy.pokemon.databinding.ViewHolderGroupPokemonBinding
import com.troy.pokemon.ui.data.GroupedPokemon

class MainPageUiAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var onClickCallback: (viewId: Int, pokemonId: Int) -> Unit
    private var groupedPokemonList = ArrayList<GroupedPokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GroupPokemonViewHolder(ViewHolderGroupPokemonBinding.inflate(inflater, parent, false), onClickCallback)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is GroupPokemonViewHolder ) {
            holder.bindViewHolder(groupedPokemonList[position])
        }
    }

    fun updateGroupedPokemonList(list: List<GroupedPokemon>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return groupedPokemonList[oldItemPosition].type == list[newItemPosition].type
            }

            override fun getOldListSize(): Int {
                return groupedPokemonList.size
            }

            override fun getNewListSize(): Int {
                return list.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return groupedPokemonList[oldItemPosition].pokemonList.size == list[newItemPosition].pokemonList.size
            }
        })

        groupedPokemonList.clear()
        groupedPokemonList.addAll(list)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return groupedPokemonList.size
    }
}