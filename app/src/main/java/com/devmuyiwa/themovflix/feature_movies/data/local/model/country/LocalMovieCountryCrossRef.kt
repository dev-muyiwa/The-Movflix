package com.devmuyiwa.themovflix.feature_movies.data.local.model.country

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["movieId", "isoValue"], indices = [Index("isoValue")])
data class LocalMovieCountryCrossRef(
    val movieId: Long = 0,
    val isoValue: String = "",
)
