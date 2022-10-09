package com.devmuyiwa.themovflix.common.data.remote.model.movieinfo


import com.devmuyiwa.themovflix.common.domain.model.Movie
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
    @field:Json(name = "vote_count") val voteCount: Long?
)

fun RemoteMovieInfo.asDomainModel() = Movie(
    movieId = movieId ?: 0,
    isAdult = isAdult ?: false,
    backdropUrl = backdropPath.orEmpty(),
    budget = budget ?: 0,
    genres = genres.orEmpty().map { it?.asDomainModel().orEmpty() },
    homepageUrl = homepage.orEmpty(),
    originalLanguage = originalLanguage.orEmpty(),
    originalTitle = originalTitle.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity ?: 0.0,
    posterUrl = posterPath.orEmpty(),
    productionCompanies = prodCompanies.orEmpty().map { it?.asDomainModel()!! },
    productionCountries = prodCountries.orEmpty().map { it?.asDomainModel()!! },
    casts = listOf(),
    releaseDate = releaseDate.orEmpty(),
    revenue = revenue ?: 0,
    runtime = runtime ?: 0,
    status = status.orEmpty(),
    tagline = tagline.orEmpty(),
    title = title.orEmpty(),
    averageVote = averageVote ?: 0.0,
    voteCount = voteCount ?: 0
)