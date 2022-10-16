package com.devmuyiwa.themovflix.feature_movies.presentation.categorisedmovies.ui

import com.devmuyiwa.themovflix.feature_movies.domain.model.CategorisedMovie
import com.devmuyiwa.themovflix.feature_movies.utils.Event

data class CategorisedMoviesState(
    val isLoading: Boolean = true,
    val categorisedMovies: List<CategorisedMovie> = emptyList(),
    val failure: Event<String>? = null,
)
