package com.troy.pokemon.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.troy.pokemon.R
import com.troy.pokemon.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainPageUiAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        observeState()
        setupOnPressed()
        viewModel.fetchAllPokemon()
    }

    private fun setupView() {
        adapter = MainPageUiAdapter().apply {
            onClickCallback = { viewId, pokeId ->
                if(viewId == R.id.iv_pokemon) {
                    showPokemonDetailPage(pokeId)
                }
            }
        }
        binding.rvGroupPokemon.layoutManager = LinearLayoutManager(this)
        binding.rvGroupPokemon.adapter = adapter
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    adapter.updateGroupedPokemonList(state.groupedPokemonList)
                }
            }
        }
    }

    private fun showPokemonDetailPage(id: Int) {
        val bundle = bundleOf("id" to id)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<PokemonDetailFragment>(R.id.fragment_container_view, args = bundle)
        }
    }

    private fun setupOnPressed() {
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.fragments.size > 0) {
                    supportFragmentManager.commit {
                        remove(supportFragmentManager.fragments.last())
                    }
                } else {
                    finish()
                }
            }
        })
    }
}