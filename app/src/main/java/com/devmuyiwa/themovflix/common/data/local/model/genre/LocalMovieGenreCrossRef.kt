package com.devmuyiwa.themovflix.common.data.local.model.genre

import androidx.room.Entity

@Entity(primaryKeys = ["movie_id", "genre_id"])
data class LocalMovieGenreCrossRef(
    val movie_id: Long,
    val genre_id: Int,
)
