package com.devmuyiwa.themovflix.feature_movies.data.local.model.genre

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "genreId"])
data class LocalMovieGenreCrossRef(
    val movieId: Long,
    val genreId: Int,
)
