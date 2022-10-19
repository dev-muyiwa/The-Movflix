package com.devmuyiwa.themovflix.feature_movies.presentation.moviedetails

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.devmuyiwa.themovflix.databinding.FragmentMovieDetailBinding
import com.devmuyiwa.themovflix.feature_movies.utils.Event
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToUpdates()
    }

    private fun subscribeToUpdates() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    updateUi(it)
                }
            }
        }
    }

    private fun updateUi(state: MovieDetailState) {
        binding.movieDetail.text = state.movie.toString()
        binding.progressBar.isVisible = state.isLoading
        handleFailure(state.failure)
    }

    private fun handleFailure(failure: Event<String>?) {
        val unhandledFailure = failure?.getContent() ?: return
        val message = "An error occurred, try again later!"
        val toast = unhandledFailure.ifEmpty { message }
        Toast.makeText(requireContext(), toast, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}