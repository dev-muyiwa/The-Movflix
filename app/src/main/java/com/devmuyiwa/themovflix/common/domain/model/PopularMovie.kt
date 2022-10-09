package com.devmuyiwa.themovflix.common.domain.model

import com.devmuyiwa.themovflix.common.domain.model.details.*

data class PopularMovie(
    val movieId: Long,
    val isAdult: Boolean,
    val backdropUrl: String,
    val genres: List<String>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterUrl: String,
    val releaseDate: String,
    val title: String,
    val averageVote: Double,
    val voteCount: Long
)
