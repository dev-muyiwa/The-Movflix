package com.devmuyiwa.themovflix.common.ui

sealed class PopularMoviesEvent {
//    data class ShowSnackbar(val message: String) : PopularMoviesEvent()
    object DisplayPopularMovies: PopularMoviesEvent()
}