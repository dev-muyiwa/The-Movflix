package com.devmuyiwa.themovflix.feature_movies.data.local.model

import androidx.room.*
import com.devmuyiwa.themovflix.feature_movies.data.local.model.cast.LocalCast
import com.devmuyiwa.themovflix.feature_movies.data.local.model.cast.LocalMovieCastCrossRef
import com.devmuyiwa.themovflix.feature_movies.data.local.model.company.*
import com.devmuyiwa.themovflix.feature_movies.data.local.model.country.*
import com.devmuyiwa.themovflix.feature_movies.data.local.model.details.LocalDetails
import com.devmuyiwa.themovflix.feature_movies.data.local.model.genre.LocalGenre
import com.devmuyiwa.themovflix.feature_movies.data.local.model.genre.LocalMovieGenreCrossRef
import com.devmuyiwa.themovflix.feature_movies.data.local.model.movie.LocalMovie
import com.devmuyiwa.themovflix.feature_movies.data.local.model.movie.asDomainModel
import com.devmuyiwa.themovflix.feature_movies.domain.model.Details
import com.devmuyiwa.themovflix.feature_movies.domain.model.MovieWithDetails

data class LocalMovieWithDetails(
    @Embedded val movie: LocalMovie,
    @Relation(
        entity = LocalGenre::class,
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(
            value = LocalMovieGenreCrossRef::class,
            parentColumn = "movieId",
            entityColumn = "genreId"
        )
    ) val genres: List<LocalGenre>? = null,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "companyId",
        associateBy = Junction(LocalMovieCompanyCrossRef::class)
    ) val productionCompanies: List<LocalProdCompany>? = null,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "isoValue",
        associateBy = Junction(LocalMovieCountryCrossRef::class)
    ) val productionCountries: List<LocalProdCountry>? = null,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "castName",
        associateBy = Junction(LocalMovieCastCrossRef::class)
    ) val casts: List<LocalCast>,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "movieId"
    ) val details: LocalDetails? = null,
)

fun LocalMovieWithDetails.asDomainModel() = MovieWithDetails(
    movie = movie.asDomainModel(),
    details = mapDetails(this@asDomainModel)
)

private fun mapDetails(details: LocalMovieWithDetails): Details =
    Details(
        budget = details.details?.budget ?: 0,
        genre = details.genres.orEmpty().map { it.genre },
        homepageUrl = details.details?.homepageUrl.orEmpty(),
        prodCompany = details.productionCompanies.orEmpty().map { it.asDomainModel() },
        prodCountry = details.productionCountries.orEmpty().map { it.asDomainModel() },
        revenue = details.details?.revenue ?: 0,
        runtime = details.details?.runtime ?: 0,
        status = details.details?.status.orEmpty(),
        tagline = details.details?.tagline.orEmpty()
    )

