package com.devmuyiwa.themovflix.feature_movies.presentation.categorisedmovies

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.devmuyiwa.themovflix.databinding.FragmentCategorisedMoviesBinding
import com.devmuyiwa.themovflix.feature_movies.presentation.MoviesEvent
import com.devmuyiwa.themovflix.feature_movies.utils.Event
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
        setupUI()
        requestMovies()
    }

    private fun setupUI() {
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        subscribeToUpdates(adapter)
    }

    private fun createAdapter(): CategorisedMoviesAdapter {
        return CategorisedMoviesAdapter(requireContext())
    }

    private fun setupRecyclerView(moviesAdapter: CategorisedMoviesAdapter) {
        binding.popularMoviesRecyclerView.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,
                false)
            hasFixedSize()
        }
    }

    private fun subscribeToUpdates(adapter: CategorisedMoviesAdapter) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    updateUi(it, adapter)
                }
            }
        }
    }

    private fun updateUi(state: CategorisedMoviesState, moviesAdapter: CategorisedMoviesAdapter) {
        moviesAdapter.submitList(state.categorisedMovies)
        binding.progressBar.isVisible = state.isLoading
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