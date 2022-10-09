package com.devmuyiwa.themovflix.common.domain.model

import com.devmuyiwa.themovflix.common.domain.model.details.*
import java.time.LocalDate
import java.time.LocalTime

data class Movie(
    val movieId: Long,
    val isAdult: Boolean,
    val backdropUrl: String,
    val budget: Long,
    val genres: List<String>,
    val homepageUrl: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterUrl: String,
    val productionCompanies: List<ProdCompany>,
    val productionCountries: List<ProdCountry>,
    val casts: List<Cast> = listOf(),
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val averageVote: Double,
    val voteCount: Long
)

