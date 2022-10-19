package com.devmuyiwa.themovflix.feature_movies.presentation.categorisedmovies.model

data class UICategorisedMovie(
    val id: Long,
    val title: String,
    val posterUrl: String,
    val genres: List<String>,
    val averageVote: Double,
    val overview: String
)
