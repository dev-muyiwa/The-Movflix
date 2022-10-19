package com.devmuyiwa.themovflix.feature_movies.presentation.moviedetails

import com.devmuyiwa.themovflix.feature_movies.domain.model.MovieWithDetails
import com.devmuyiwa.themovflix.feature_movies.utils.Event

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: MovieWithDetails? = null,
    val failure: Event<String>? = null
)
