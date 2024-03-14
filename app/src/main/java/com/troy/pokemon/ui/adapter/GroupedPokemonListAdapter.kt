package com.troy.pokemon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.troy.pokemon.data.ACTION_CAPTURE
import com.troy.pokemon.data.ACTION_RELEASE
import com.troy.pokemon.data.ACTION_TYPE
import com.troy.pokemon.databinding.ViewHolderGroupPokemonBinding
import com.troy.pokemon.databinding.ViewHolderMyPokemonBinding
import com.troy.pokemon.ui.viewholder.GroupPokemonViewHolder
import com.troy.pokemon.ui.data.GroupedPokemon
import com.troy.pokemon.ui.data.Pokemon
import com.troy.pokemon.ui.viewholder.MyPokemonViewHolder

class GroupedPokemonListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var onClickCallback: OnClickCallback
    private var groupedPokemonList = ArrayList<GroupedPokemon>()
    private var myPokemonList = ArrayList<Pokemon>()

    companion object {
        private const val TYPE_MY_POKEMON = 0
        private const val TYPE_ALL_POKEMON = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == TYPE_MY_POKEMON) {
            return MyPokemonViewHolder(ViewHolderMyPokemonBinding.inflate(inflater, parent, false)) {
                    viewId, bundle -> onClickCallback.onClick(viewId, bundle.also { it.putInt(ACTION_TYPE, ACTION_RELEASE) })
            }
        }
        return GroupPokemonViewHolder(ViewHolderGroupPokemonBinding.inflate(inflater, parent, false)) {
                viewId, bundle -> onClickCallback.onClick(viewId, bundle.also { it.putInt(ACTION_TYPE, ACTION_CAPTURE) })
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is GroupPokemonViewHolder) {
            holder.bindViewHolder(groupedPokemonList[position - 1])
        } else if (holder is MyPokemonViewHolder) {
            holder.bindViewHolder(myPokemonList)
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

    fun updateMyPokemon(list: List<Pokemon>) {
        myPokemonList.clear()
        myPokemonList.addAll(list)

        notifyItemChanged(0)
    }

    override fun getItemCount(): Int {
        return groupedPokemonList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0)
            TYPE_MY_POKEMON
        else
            TYPE_ALL_POKEMON
    }
}