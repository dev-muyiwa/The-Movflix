package com.devmuyiwa.themovflix.popularmovie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devmuyiwa.themovflix.common.domain.Resource
import com.devmuyiwa.themovflix.common.domain.model.CategorisedMovie
import com.devmuyiwa.themovflix.common.ui.Event
import com.devmuyiwa.themovflix.common.ui.PopularMoviesEvent
import com.devmuyiwa.themovflix.popularmovie.domain.usecases.FetchPopularMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesFragmentViewModel @Inject constructor(
    private val fetchPopularMovie: FetchPopularMovie,
) : ViewModel() {
    init {
        subscribeToUpdates()
    }

    private var currentPage = 1
    private val _state = MutableStateFlow(PopularMoviesUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: PopularMoviesEvent) {
        when (event) {
            is PopularMoviesEvent.DisplayPopularMovies -> fetchPopularMovies()
        }
    }

    private fun subscribeToUpdates() {
        viewModelScope.launch {
            fetchPopularMovie(currentPage).onEach { result ->
                when (result) {
                    is Resource.Loading -> onLoading(result)
                    is Resource.Success -> onSuccess(result)
                    is Resource.Error -> onFailure(result)
                }
            }.launchIn(this)
        }
    }

    private fun onLoading(result: Resource<List<CategorisedMovie>>) {
        _state.update { oldState ->
            oldState.copy(
                isLoading = true,
                popularMoviesItem = result.data.orEmpty()
            )
        }
    }

    private fun onSuccess(result: Resource<List<CategorisedMovie>>) {
        _state.update { oldState ->
            oldState.copy(
                isLoading = false,
                popularMoviesItem = result.data.orEmpty()
            )
        }
//        currentPage++
    }

    private fun fetchPopularMovies() {
        if (state.value.popularMoviesItem.isEmpty()) {
            subscribeToUpdates()
        }
    }

    private fun onFailure(result: Resource<List<CategorisedMovie>>) {
        _state.update { oldState ->
            oldState.copy(
                isLoading = false,
                popularMoviesItem = result.data.orEmpty(),
                failure = Event(result.message.orEmpty())
            )
        }
    }
}