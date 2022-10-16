package com.devmuyiwa.themovflix.feature_movies.domain.model

data class Movie(
    val movieId: Long,
    val isAdult: Boolean,
    val backdropUrl: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterUrl: String,
    val releaseDate: String,
    val title: String,
    val averageVote: Double,
    val voteCount: Long,
)

