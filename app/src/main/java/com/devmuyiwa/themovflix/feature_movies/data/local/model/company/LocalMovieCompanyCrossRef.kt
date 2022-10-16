package com.devmuyiwa.themovflix.feature_movies.data.local.model.company

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "companyId"])
data class LocalMovieCompanyCrossRef(
    val movieId: Long = 0,
    val companyId: Long = 0,
)
