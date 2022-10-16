package com.devmuyiwa.themovflix.feature_movies.presentation.categorisedmovies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devmuyiwa.themovflix.feature_movies.utils.Category
import com.devmuyiwa.themovflix.core.util.Resource
import com.devmuyiwa.themovflix.feature_movies.domain.model.CategorisedMovie
import com.devmuyiwa.themovflix.feature_movies.utils.Event
import com.devmuyiwa.themovflix.feature_movies.presentation.MoviesEvent
import com.devmuyiwa.themovflix.feature_movies.domain.usecases.GetMoviesByCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategorisedMoviesViewModel @Inject constructor(
    private val getMoviesByCategory: GetMoviesByCategory,
) : ViewModel() {
    private var currentPage = 1
    private val _state = MutableStateFlow(CategorisedMoviesState())
    val state = _state.asStateFlow()

    init {
//        subscribeToUpdates()
    }

    fun onEvent(event: MoviesEvent) {
        when (event) {
            is MoviesEvent.DisplayPopularMovies -> fetchPopularMovies()
            is MoviesEvent.DisplayNowPlaying -> fetchNowPlaying()
            is MoviesEvent.DisplayRecommendedMovies -> fetchRecommended()
            is MoviesEvent.DisplayTopRatedMovies -> fetchTopRated()
            is MoviesEvent.DisplayUpcomingMovies -> fetchUpComing()
        }
    }

    private fun subscribeToUpdates(category: Category) {
        viewModelScope.launch {
            getMoviesByCategory(currentPage, category).onEach { result ->
                when (result) {
                    is Resource.Loading -> onLoading(result)
                    is Resource.Success -> onSuccess(result)
                    is Resource.Error -> onFailure(result)
                }
            }.launchIn(this)

        }
    }

    private fun fetchPopularMovies() {
        if (state.value.categorisedMovies.isEmpty()) {
            subscribeToUpdates(Category.POPULAR)
        }
    }

    private fun fetchNowPlaying(){
        if (state.value.categorisedMovies.isEmpty()) {
            subscribeToUpdates(Category.NOW_PLAYING)
        }
    }

    private fun fetchRecommended() {
        if (state.value.categorisedMovies.isEmpty()) {
            subscribeToUpdates(Category.RECOMMENDED)
        }
    }

    private fun fetchTopRated(){
        if (state.value.categorisedMovies.isEmpty()) {
            subscribeToUpdates(Category.TOP_RATED)
        }
    }

    private fun fetchUpComing() {
        if (state.value.categorisedMovies.isEmpty()) {
            subscribeToUpdates(Category.UPCOMING)
        }
    }

    private fun onLoading(result: Resource<List<CategorisedMovie>>) {
        _state.update { oldState ->
            oldState.copy(
                isLoading = true,
                categorisedMovies = result.data.orEmpty()
            )
        }
    }

    private fun onSuccess(result: Resource<List<CategorisedMovie>>) {
        _state.update { oldState ->
            oldState.copy(
                isLoading = false,
                categorisedMovies = result.data.orEmpty()
            )
        }
//        currentPage++
    }

    private fun onFailure(result: Resource<List<CategorisedMovie>>) {
        _state.update { oldState ->
            oldState.copy(
                isLoading = false,
                categorisedMovies = result.data.orEmpty(),
                failure = Event(result.message.orEmpty())
            )
        }
    }
}