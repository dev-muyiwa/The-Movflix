package com.devmuyiwa.themovflix.common.data.remote.model.movieinfo


import com.devmuyiwa.themovflix.common.domain.model.details.ProdCountry
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