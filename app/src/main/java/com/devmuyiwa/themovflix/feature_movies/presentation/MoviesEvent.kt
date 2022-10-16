package com.devmuyiwa.themovflix.feature_movies.presentation

sealed class MoviesEvent {
//    data class ShowSnackbar(val message: String) : MoviesEvent()
    object DisplayPopularMovies: MoviesEvent()
    object DisplayNowPlaying: MoviesEvent()
    object DisplayTopRatedMovies: MoviesEvent()
    object DisplayRecommendedMovies: MoviesEvent()
    object DisplayUpcomingMovies: MoviesEvent()
}