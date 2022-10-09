package com.devmuyiwa.themovflix.common.data.remote.model.popularmovie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemotePopularMovieDto(
    @field:Json(name = "results") val movies: List<RemotePopularMovie?>?
)
