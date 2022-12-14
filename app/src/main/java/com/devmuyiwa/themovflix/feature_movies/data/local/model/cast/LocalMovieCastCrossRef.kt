package com.devmuyiwa.themovflix.feature_movies.data.local.model.cast

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["movieId", "castName"], indices = [Index("castName")])
data class LocalMovieCastCrossRef(
    val movieId: Long = 0,
    val castName: String = "",
)
