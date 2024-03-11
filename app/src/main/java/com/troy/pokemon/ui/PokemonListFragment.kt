package com.troy.pokemon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.troy.pokemon.R
import com.troy.pokemon.databinding.FragmentPokemonListBinding
import com.troy.pokemon.ui.adapter.MainPageUiAdapter
import com.troy.pokemon.ui.viewmodel.MainViewModel
import com.troy.pokemon.ui.viewmodel.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListFragment: Fragment() {
    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!
    private val viewModel : PokemonListViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: MainPageUiAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeState()
        viewModel.fetchAllPokemon()
    }

    private fun setupView() {
        adapter = MainPageUiAdapter().apply {
            onClickCallback = { viewId, pokeId ->
                when(viewId) {
                    R.id.iv_pokemon -> {
                        activityViewModel.showPokemonDetail(pokeId)
                        onHiddenChanged(true)
                        viewModel.stopFetch()
                    }
                    R.id.iv_poke_ball -> {
                        viewModel.capturePokemon(pokeId)
                    }
                }
            }
        }
        binding.rvGroupPokemon.layoutManager = LinearLayoutManager(context)
        binding.rvGroupPokemon.adapter = adapter

        parentFragmentManager.addOnBackStackChangedListener {
            if (parentFragmentManager.backStackEntryCount == 1) {
                viewModel.stopFetch()
            } else if (parentFragmentManager.backStackEntryCount == 0) {
                viewModel.fetchAllPokemon()
            }
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                adapter.updateGroupedPokemonList(state.groupedPokemonList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}