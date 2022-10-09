package com.devmuyiwa.themovflix.common.data.remote.model.movieinfo


import com.devmuyiwa.themovflix.common.domain.model.details.ProdCompany
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteProdCompany(
    @field:Json(name = "id") val companyId: Long?,
    @field:Json(name = "logo_path") val logoPath: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "origin_country") val originCountry: String?
)

fun RemoteProdCompany.asDomainModel() = ProdCompany(
    companyId = companyId ?: 0,
    logoUrl = logoPath.orEmpty(),
    name = name.orEmpty(),
    country = originCountry.orEmpty()
)