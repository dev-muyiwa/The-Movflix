package com.devmuyiwa.themovflix.popularmovie.ui

import com.devmuyiwa.themovflix.common.domain.model.CategorisedMovie
import com.devmuyiwa.themovflix.common.ui.Event

data class PopularMoviesUiState(
    val isLoading: Boolean = true,
    val popularMoviesItem: List<CategorisedMovie> = emptyList(),
    val failure: Event<String>? = null,
)
