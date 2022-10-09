package com.devmuyiwa.themovflix.common.data.local.model.genre

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["movieId", "genre"])
data class LocalMovieGenreCrossRef(
    val movieId: Long,
    val genre: String,
)
