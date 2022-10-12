package com.devmuyiwa.themovflix.common.domain.model

data class CategorisedMovie(
    val movie: Movie,
    val genres: List<String>,
    val category: String,
)
