package com.troy.pokemon.ui

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.troy.pokemon.R
import com.troy.pokemon.databinding.ActivityMainBinding
import com.troy.pokemon.ui.state.MainState
import com.troy.pokemon.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeState()
        setupOnPressed()
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when(state) {
                        is MainState.ShowPokemonDetail -> {
                            showPokemonDetailPage(state.id)
                        }
                    }
                }
            }
        }
    }

    private fun showPokemonDetailPage(id: Int) {
        val bundle = bundleOf(PokemonDetailFragment.KEY_ID to id)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<PokemonDetailFragment>(R.id.fragment_container_view, args = bundle)
            addToBackStack("PokemonDetailFragment")
        }
    }

    private fun setupOnPressed() {
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.fragments.size > 1) {
                    supportFragmentManager.popBackStack()
                } else {
                    finish()
                }
            }
        })
    }
}