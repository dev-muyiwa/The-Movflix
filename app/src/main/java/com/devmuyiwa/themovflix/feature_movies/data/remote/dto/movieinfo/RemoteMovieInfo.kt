package com.devmuyiwa.themovflix.feature_movies.data.remote.dto.movieinfo


import com.devmuyiwa.themovflix.feature_movies.data.local.model.LocalMovieWithDetails
import com.devmuyiwa.themovflix.feature_movies.data.local.model.company.LocalProdCompany
import com.devmuyiwa.themovflix.feature_movies.data.local.model.country.LocalProdCountry
import com.devmuyiwa.themovflix.feature_movies.data.local.model.details.LocalDetails
import com.devmuyiwa.themovflix.feature_movies.data.local.model.genre.LocalGenre
import com.devmuyiwa.themovflix.feature_movies.data.local.model.movie.LocalMovie
import com.devmuyiwa.themovflix.feature_movies.data.remote.util.*
import com.devmuyiwa.themovflix.feature_movies.domain.model.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteMovieInfo(
    @field:Json(name = "adult") val isAdult: Boolean?,
    @field:Json(name = "backdrop_path") val backdropPath: String?,
    @field:Json(name = "budget") val budget: Long?,
    @field:Json(name = "genres") val genres: List<RemoteGenre?>?,
    @field:Json(name = "homepage") val homepage: String?,
    @field:Json(name = "id") val movieId: Long?,
    @field:Json(name = "original_language") val originalLanguage: String?,
    @field:Json(name = "original_title") val originalTitle: String?,
    @field:Json(name = "overview") val overview: String?,
    @field:Json(name = "popularity") val popularity: Double?,
    @field:Json(name = "poster_path") val posterPath: String?,
    @field:Json(name = "production_companies") val prodCompanies: List<RemoteProdCompany?>?,
    @field:Json(name = "production_countries") val prodCountries: List<RemoteProdCountry?>?,
    @field:Json(name = "release_date") val releaseDate: String?,
    @field:Json(name = "revenue") val revenue: Long?,
    @field:Json(name = "runtime") val runtime: Int?,
    @field:Json(name = "status") val status: String?,
    @field:Json(name = "tagline") val tagline: String?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "video") val hasVideo: Boolean?,
    @field:Json(name = "vote_average") val averageVote: Double?,
    @field:Json(name = "vote_count") val voteCount: Long?,
)

fun RemoteMovieInfo.asDomainModel() = Movie(
    movieId = movieId ?: 0,
    isAdult = isAdult ?: false,
    backdropUrl = IMAGE_BASE_ENDPOINT + BACKDROP_SIZE + backdropPath.orEmpty(),
    originalLanguage = originalLanguage.orEmpty(),
    originalTitle = originalTitle.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity ?: 0.0,
    posterUrl = IMAGE_BASE_ENDPOINT + POSTER_SIZE + posterPath.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    title = title.orEmpty(),
    averageVote = averageVote ?: 0.0,
    voteCount = voteCount ?: 0
)

fun RemoteMovieInfo.asEntityModel() = LocalMovie(
    movieId = movieId ?: 0,
    isAdult = isAdult ?: false,
    backdropUrl = IMAGE_BASE_ENDPOINT + BACKDROP_SIZE + backdropPath.orEmpty(),
    originalLanguage = originalLanguage.orEmpty(),
    originalTitle = originalTitle.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity ?: 0.0,
    posterUrl = IMAGE_BASE_ENDPOINT + POSTER_SIZE + posterPath.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    title = title.orEmpty(),
    averageVote = averageVote ?: 0.0,
    voteCount = voteCount ?: 0
)

fun RemoteMovieInfo.asEntity() = LocalMovieWithDetails(
    movie = this.asEntityModel(),
    genres = genres.orEmpty().map { LocalGenre(it?.genreId ?: 0, it?.genreName.orEmpty()) },
    productionCompanies = prodCompanies.orEmpty().map {
        LocalProdCompany(
            companyId = it?.companyId ?: 0,
            logoUrl = it?.logoPath.orEmpty(),
            name = it?.name.orEmpty(),
            country = it?.originCountry.orEmpty()
        )
    },
    productionCountries = prodCountries.orEmpty().map {
        LocalProdCountry(isoValue = it?.isoValue.orEmpty(), name = it?.name.orEmpty())
    },
    casts = listOf(),
    details = mapDetails(this)
)

private fun mapDetails(info: RemoteMovieInfo): LocalDetails =
    LocalDetails(
        budget = info.budget ?: 0,
        movieId = info.movieId ?: 0,
        homepageUrl = info.homepage.orEmpty(),
        revenue = info.revenue ?: 0,
        runtime = info.runtime ?: 0,
        status = info.status.orEmpty(),
        tagline = info.tagline.orEmpty()
    )