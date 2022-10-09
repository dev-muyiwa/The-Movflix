package com.devmuyiwa.themovflix.common.data.remote.model.movieinfo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteGenre(
    @field:Json(name = "id") val genreId: Int?,
    @field:Json(name = "name") val genreName: String?
)

fun RemoteGenre.asDomainModel() = genreName