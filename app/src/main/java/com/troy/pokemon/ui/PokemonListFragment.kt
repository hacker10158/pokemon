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
import com.troy.pokemon.data.ACTION_CAPTURE
import com.troy.pokemon.data.ACTION_RELEASE
import com.troy.pokemon.data.ACTION_TYPE
import com.troy.pokemon.data.POKE_ID
import com.troy.pokemon.data.UID
import com.troy.pokemon.databinding.FragmentPokemonListBinding
import com.troy.pokemon.ui.adapter.GroupedPokemonListAdapter
import com.troy.pokemon.ui.state.PokemonListState
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
    private lateinit var adapter: GroupedPokemonListAdapter

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
        adapter = GroupedPokemonListAdapter().apply {
            onClickCallback = { viewId, bundle ->
                when(viewId) {
                    R.id.iv_pokemon -> {
                        activityViewModel.showPokemonDetail(bundle.getInt(POKE_ID))
                        onHiddenChanged(true)
                        viewModel.stopFetch()
                    }
                    R.id.iv_poke_ball -> {
                        when(bundle.getInt(ACTION_TYPE)) {
                            ACTION_CAPTURE -> viewModel.capturePokemon(bundle.getInt(POKE_ID))
                            ACTION_RELEASE -> viewModel.releasePokemon(bundle.getInt(UID))
                        }
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
                when(state) {
                    is PokemonListState.OnPokemonListChanged -> {
                        adapter.updateGroupedPokemonList(state.groupedPokemonList)
                    }
                    is PokemonListState.OnMyPokemonChanged -> {
                        adapter.updateMyPokemon(state.myPokemonList)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}