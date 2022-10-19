package com.devmuyiwa.themovflix.feature_movies.presentation.categorisedmovies

import com.devmuyiwa.themovflix.feature_movies.domain.model.CategorisedMovie
import com.devmuyiwa.themovflix.feature_movies.presentation.categorisedmovies.model.UICategorisedMovie
import com.devmuyiwa.themovflix.feature_movies.utils.Event

data class CategorisedMoviesState(
    val isLoading: Boolean = true,
    val categorisedMovies: List<UICategorisedMovie> = emptyList(),
    val failure: Event<String>? = null,
)
