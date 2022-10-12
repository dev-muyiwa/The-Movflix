package com.devmuyiwa.themovflix.common.data.local.model

import androidx.room.*
import com.devmuyiwa.themovflix.common.data.local.model.cast.LocalCast
import com.devmuyiwa.themovflix.common.data.local.model.cast.LocalMovieCastCrossRef
import com.devmuyiwa.themovflix.common.data.local.model.company.*
import com.devmuyiwa.themovflix.common.data.local.model.country.*
import com.devmuyiwa.themovflix.common.data.local.model.details.LocalDetails
import com.devmuyiwa.themovflix.common.data.local.model.genre.LocalGenre
import com.devmuyiwa.themovflix.common.data.local.model.genre.LocalMovieGenreCrossRef
import com.devmuyiwa.themovflix.common.data.local.model.movie.LocalMovie
import com.devmuyiwa.themovflix.common.data.local.model.movie.asDomainModel
import com.devmuyiwa.themovflix.common.domain.model.Details
import com.devmuyiwa.themovflix.common.domain.model.MovieWithDetails

data class LocalMovieWithDetails(
    @Embedded val movie: LocalMovie,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "genre",
        associateBy = Junction(LocalMovieGenreCrossRef::class)
    ) val genres: List<LocalGenre>,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "companyId",
        associateBy = Junction(LocalMovieCompanyCrossRef::class)
    ) val productionCompanies: List<LocalProdCompany>,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "isoValue",
        associateBy = Junction(LocalMovieCountryCrossRef::class)
    ) val productionCountries: List<LocalProdCountry>,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "castName",
        associateBy = Junction(LocalMovieCastCrossRef::class)
    ) val casts: List<LocalCast>,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "movieId"
    ) val details: LocalDetails,
)

fun LocalMovieWithDetails.asDomainModel() = MovieWithDetails(
    movie = movie.asDomainModel(),
    details = mapDetails(this@asDomainModel)
)

private fun mapDetails(details: LocalMovieWithDetails): Details =
    Details(
        budget = details.details.budget,
        genre = details.genres.map { it.genre },
        homepageUrl = details.details.homepageUrl,
        prodCompany = details.productionCompanies.map { it.asDomainModel() },
        prodCountry = details.productionCountries.map { it.asDomainModel() },
        revenue = details.details.revenue,
        runtime = details.details.runtime,
        status = details.details.status,
        tagline = details.details.tagline
    )

