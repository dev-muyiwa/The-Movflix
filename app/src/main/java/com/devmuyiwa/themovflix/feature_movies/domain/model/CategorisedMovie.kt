package com.devmuyiwa.themovflix.feature_movies.domain.model

data class CategorisedMovie(
    val movie: Movie,
    val genres: List<String>,
    val category: String,
)
