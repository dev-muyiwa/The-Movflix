package com.devmuyiwa.themovflix.common.domain.model

import com.devmuyiwa.themovflix.common.domain.model.details.ProdCompany
import com.devmuyiwa.themovflix.common.domain.model.details.ProdCountry

data class MovieWithDetails(
    val movie: Movie,
    val details: Details
)

data class Details(
    val budget: Long,
    val genre: List<String>,
    val homepageUrl: String,
    val prodCompany: List<ProdCompany>,
    val prodCountry: List<ProdCountry>,
    val revenue: Long,
    val runtime: Int,
    val status: String,
    val tagline: String
)

data class ProdCompany(
    val companyId: Long,
    val logoUrl: String,
    val name: String,
    val country: String
)

data class ProdCountry(
    val isoValue: String,
    val name: String
)

