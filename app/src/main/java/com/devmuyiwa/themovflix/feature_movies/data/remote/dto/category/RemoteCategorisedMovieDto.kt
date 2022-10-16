package com.devmuyiwa.themovflix.feature_movies.data.remote.dto.category

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteCategorisedMovieDto(
    @field:Json(name = "results") val movies: List<RemoteCategorisedMovie?>?
)
