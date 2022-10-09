package com.devmuyiwa.themovflix.common

import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.*
import com.devmuyiwa.themovflix.R
import com.devmuyiwa.themovflix.common.ui.Event
import com.devmuyiwa.themovflix.common.ui.PopularMoviesEvent
import com.devmuyiwa.themovflix.popularmovie.ui.PopularMoviesFragmentViewModel
import com.devmuyiwa.themovflix.popularmovie.ui.PopularMoviesUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: PopularMoviesFragmentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeToUpdates()
        requestMovies()
    }

    private fun subscribeToUpdates() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { updateUi(it) }
            }
        }
    }

    private fun updateUi(state: PopularMoviesUiState) {
        val progressBar: ProgressBar = findViewById(R.id.progress)
        val test: TextView = findViewById(R.id.textview)
        progressBar.isVisible = state.isLoading
        test.text = state.popularMoviesItem.toString()
        handleFailure(state.failure)
    }

    private fun requestMovies() {
        viewModel.onEvent(PopularMoviesEvent.DisplayPopularMovies)
    }

    private fun handleFailure(failure: Event<String>?) {
        val unhandledFailure = failure?.getContent() ?: return
        val message = "An error occurred, try again later!"
        val toast = unhandledFailure.ifEmpty { message }
        Toast.makeText(this@MainActivity, toast, Toast.LENGTH_LONG).show()

    }
}