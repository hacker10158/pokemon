package com.troy.pokemon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.troy.pokemon.data.firstToUpperCase
import com.troy.pokemon.databinding.FragmentPokemonDetailBinding
import com.troy.pokemon.ui.data.Pokemon
import com.troy.pokemon.ui.viewmodel.PokemonDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonDetailFragment: Fragment() {
    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel : PokemonDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireArguments().getInt("id")

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                state.pokemon?.let {
                    updateView(it)
                }
            }
        }

        viewModel.observePokemon(id)
    }

    private fun updateView(pokemon: Pokemon) {
        binding.tvName.text = pokemon.name.firstToUpperCase()
        Glide.with(this).load(pokemon.imageUrl).into(binding.ivPokemon)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}