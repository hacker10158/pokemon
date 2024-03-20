package com.troy.pokemon.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.troy.pokemon.R
import com.troy.pokemon.data.TransitionCache
import com.troy.pokemon.databinding.ActivityMainBinding
import com.troy.pokemon.ui.state.MainEvent
import com.troy.pokemon.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        showErrorMessage(throwable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeEvent()
        setupOnPressed()
    }

    private fun observeEvent() {
        lifecycleScope.launch(exceptionHandler) {
            viewModel.event.collect { event ->
                when(event) {
                    is MainEvent.ShowPokemonDetail -> {
                        showPokemonDetailPage(event.id)
                    }
                    is MainEvent.OnError -> {
                        showErrorMessage(event.throwable)
                    }
                }
            }
        }
    }

    private fun showPokemonDetailPage(id: Int) {
        val bundle = bundleOf(PokemonDetailFragment.KEY_ID to id)

        TransitionCache.getTransitionView()?.let {
            ViewCompat.setTransitionName(it, "pokemon_image")
        }

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            TransitionCache.getTransitionView()?.let {
                addSharedElement(it, it.transitionName)
            }

            replace<PokemonDetailFragment>(R.id.fragment_container_view, args = bundle)
            addToBackStack("PokemonDetailFragment")
        }
    }

    private fun setupOnPressed() {
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.fragments.last() is PokemonDetailFragment) {
                    supportFragmentManager.popBackStack()
                } else {
                    finish()
                }
            }
        })
    }

    private fun showErrorMessage(throwable: Throwable) {
        val message = throwable.message?: throwable.toString()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}