package com.devmuyiwa.themovflix.feature_movies.domain.model

import com.devmuyiwa.themovflix.feature_movies.presentation.categorisedmovies.model.UICategorisedMovie

data class CategorisedMovie(
    val movie: Movie,
    val genres: List<String>,
    val category: String,
)

fun CategorisedMovie.asUIModel() : UICategorisedMovie {
    return UICategorisedMovie(
        id = movie.movieId,
        title = movie.title,
        posterUrl = movie.posterUrl,
        genres = genres,
        averageVote = String.format("%.1f", movie.averageVote).toDouble(),
        overview = movie.overview
    )
}
