package com.devmuyiwa.themovflix.feature_movies.presentation.moviedetails

import androidx.lifecycle.*
import com.devmuyiwa.themovflix.core.util.Resource
import com.devmuyiwa.themovflix.feature_movies.data.remote.util.MOVIE_ID
import com.devmuyiwa.themovflix.feature_movies.domain.model.MovieWithDetails
import com.devmuyiwa.themovflix.feature_movies.utils.Event
import com.devmuyiwa.themovflix.feature_movies.domain.usecases.GetMovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetail: GetMovieDetail,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(MovieDetailState())
    val state: StateFlow<MovieDetailState> = _state.asStateFlow()

    init {
        savedStateHandle.get<Long>(MOVIE_ID)?.let {
            getMovieInfo(it)
        }
    }

    private fun getMovieInfo(movieId: Long) {
        viewModelScope.launch {
            getMovieDetail(movieId).onEach { result ->
                when (result) {
                    is Resource.Loading -> onLoading(result)
                    is Resource.Success -> onSuccess(result)
                    is Resource.Error -> onFailure(result)
                }
            }.launchIn(this)
        }
    }

    private fun onLoading(result: Resource<MovieWithDetails>) {
        _state.update { oldState ->
            oldState.copy(
                isLoading = true,
                movie = result.data
            )
        }
    }

    private fun onSuccess(result: Resource<MovieWithDetails>) {
        _state.update { oldState ->
            oldState.copy(
                isLoading = false,
                movie = result.data
            )
        }
    }

    private fun onFailure(result: Resource<MovieWithDetails>) {
        _state.update { oldState ->
            oldState.copy(
                isLoading = false,
                movie = result.data,
                failure = Event(result.message.orEmpty())
            )
        }
    }
}
