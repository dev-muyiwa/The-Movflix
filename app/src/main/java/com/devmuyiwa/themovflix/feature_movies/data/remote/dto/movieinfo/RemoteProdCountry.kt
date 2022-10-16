package com.devmuyiwa.themovflix.feature_movies.data.remote.dto.movieinfo


import com.devmuyiwa.themovflix.feature_movies.domain.model.ProdCountry
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteProdCountry(
    @field:Json(name = "iso_3166_1") val isoValue: String?,
    @field:Json(name = "name") val name: String?
)

fun RemoteProdCountry.asDomainModel() = ProdCountry(
    isoValue = isoValue.orEmpty(),
    name = name.orEmpty()
)