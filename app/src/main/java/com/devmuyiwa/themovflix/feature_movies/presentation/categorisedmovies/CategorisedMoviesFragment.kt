package com.devmuyiwa.themovflix.feature_movies.presentation.categorisedmovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.devmuyiwa.themovflix.feature_movies.utils.Event
import com.devmuyiwa.themovflix.feature_movies.presentation.MoviesEvent
import com.devmuyiwa.themovflix.databinding.FragmentCategorisedMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategorisedMoviesFragment : Fragment() {
    private var _binding: FragmentCategorisedMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CategorisedMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCategorisedMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    private fun updateUi(state: CategorisedMoviesState) {
        binding.progressBar.isVisible = state.isLoading
        binding.moviesText.text = state.categorisedMovies.toString()
        handleFailure(state.failure)
    }

    private fun handleFailure(failure: Event<String>?) {
        val unhandledFailure = failure?.getContent() ?: return
        val message = "An error occurred, try again later!"
        val toast = unhandledFailure.ifEmpty { message }
        Toast.makeText(requireContext(), toast, Toast.LENGTH_LONG).show()
    }

    private fun requestMovies() {
        viewModel.onEvent(MoviesEvent.DisplayTopRatedMovies)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}