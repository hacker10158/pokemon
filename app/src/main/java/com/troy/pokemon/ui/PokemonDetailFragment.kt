package com.troy.pokemon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.troy.pokemon.R
import com.troy.pokemon.data.firstToUpperCase
import com.troy.pokemon.databinding.FragmentPokemonDetailBinding
import com.troy.pokemon.ui.data.Pokemon
import com.troy.pokemon.ui.state.PokemonDetailEvent
import com.troy.pokemon.ui.viewmodel.MainViewModel
import com.troy.pokemon.ui.viewmodel.PokemonDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonDetailFragment: Fragment() {
    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!
    private val activityViewModel: MainViewModel by activityViewModels()
    private val viewModel : PokemonDetailViewModel by viewModels()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        showErrorMessage(throwable)
    }

    companion object {
        const val KEY_ID = "id"
    }

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
        val id = requireArguments().getInt(KEY_ID)

        setupOnClickListener()

        observeEvent()
        observeState()

        viewModel.observePokemon(id)
    }

    private fun observeState() {
        lifecycleScope.launch(exceptionHandler) {
            viewModel.state.collect { state ->
                state.pokemon?.let {
                    updateView(it, state.evolveFromPokemon)
                }
            }
        }
    }

    private fun observeEvent() {
        lifecycleScope.launch(exceptionHandler) {
            viewModel.event.collect { event ->
                when (event) {
                    is PokemonDetailEvent.OnError -> {
                        showErrorMessage(event.throwable)
                    }
                }
            }
        }
    }

    private fun setupOnClickListener() {
        binding.ivBack.setOnClickListener {
            parentFragmentManager.commit {
                remove(this@PokemonDetailFragment)
            }
        }
    }

    private fun updateView(pokemon: Pokemon, evolvesFrom: Pokemon?) {
        binding.tvName.text = pokemon.name.firstToUpperCase()
        binding.tvNumber.text = resources.getString(R.string.pokemon_number, pokemon.id)
        binding.tvFlavor.text = pokemon.flavorText
        Glide.with(this).load(pokemon.imageUrl).into(binding.ivPokemon)
        evolvesFrom?.let { poke ->
            binding.layoutEvolveFrom.visibility = View.VISIBLE
            binding.tvEvolveFromName.text = poke.name.firstToUpperCase()
            Glide.with(this).load(poke.imageUrl).into(binding.ivEvolvePokemon)
            binding.ivEvolvePokemon.setOnClickListener {
                activityViewModel.showPokemonDetail(poke.id)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showErrorMessage(throwable: Throwable) {
        Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
    }
}