package com.devmuyiwa.themovflix.common.data.local.model

import androidx.room.*
import com.devmuyiwa.themovflix.common.data.local.model.cast.*
import com.devmuyiwa.themovflix.common.data.local.model.company.*
import com.devmuyiwa.themovflix.common.data.local.model.country.*
import com.devmuyiwa.themovflix.common.data.local.model.genre.*
import com.devmuyiwa.themovflix.common.domain.model.Movie

data class LocalMovieAggregate(
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
)

fun LocalMovieAggregate.asDomainModel() = Movie(
    movieId = movie.movieId,
    isAdult = movie.adult,
    backdropUrl = movie.backdropUrl,
    budget = movie.budget,
    genres = genres.map { it.asDomainModel() },
    homepageUrl = movie.homepageUrl,
    originalLanguage = movie.originalLanguage,
    originalTitle = movie.originalTitle,
    overview = movie.overview,
    popularity = movie.popularity,
    posterUrl = movie.posterUrl,
    productionCompanies = productionCompanies.map { it.asDomainModel() },
    productionCountries = productionCountries.map { it.asDomainModel() },
    casts = casts.map { it.asDomainModel() },
    releaseDate = movie.releaseDate,
    revenue = movie.revenue,
    runtime = movie.runtime,
    status = movie.status,
    tagline = movie.tagline,
    title = movie.title,
    averageVote = movie.averageVote,
    voteCount = movie.voteCount
)
