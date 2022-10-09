package com.devmuyiwa.themovflix.common.data.remote.model.popularmovie


import com.devmuyiwa.themovflix.common.data.local.model.genre.LocalGenre
import com.devmuyiwa.themovflix.common.data.local.model.popularmovie.LocalPopularMovie
import com.devmuyiwa.themovflix.common.data.local.model.popularmovie.PopularMovieWithGenre
import com.devmuyiwa.themovflix.common.domain.model.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemotePopularMovie(
    @field:Json(name = "adult") val isAdult: Boolean?,
    @field:Json(name = "backdrop_path") val backdropPath: String?,
    @field:Json(name = "genre_ids") val genreIds: List<Int?>?,
    @field:Json(name = "id") val movieId: Long?,
    @field:Json(name = "original_language") val originalLanguage: String?,
    @field:Json(name = "original_title") val originalTitle: String?,
    @field:Json(name = "overview") val overview: String?,
    @field:Json(name = "popularity") val popularity: Double?,
    @field:Json(name = "poster_path") val posterPath: String?,
    @field:Json(name = "release_date") val releaseDate: String?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "video") val hasVideo: Boolean?,
    @field:Json(name = "vote_average") val averageVote: Double?,
    @field:Json(name = "vote_count") val voteCount: Long?,
)

fun RemotePopularMovie.asDomainModel() = PopularMovie(
    movieId = movieId ?: 0,
    isAdult = isAdult ?: false,
    backdropUrl = backdropPath.orEmpty(),
//    genres = genreIds.orEmpty().map { GenreType.fromApi(it ?: 0) },
    genres = genreIds.orEmpty().map { it.toString() },
    originalLanguage = originalLanguage.orEmpty(),
    originalTitle = originalTitle.orEmpty(),
    overview = overview.orEmpty(),
    popularity = popularity ?: 0.0,
    posterUrl = posterPath.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    title = title.orEmpty(),
    averageVote = averageVote ?: 0.0,
    voteCount = voteCount ?: 0
)

fun RemotePopularMovie.asEntityModel() = PopularMovieWithGenre(
    popularMovie = LocalPopularMovie(movieId ?: 0, isAdult ?: false,
        backdropPath.orEmpty(), originalLanguage.orEmpty(), originalTitle.orEmpty(),
        overview.orEmpty(), popularity ?: 0.0, posterPath.orEmpty(),
    releaseDate.orEmpty(), title.orEmpty(), averageVote ?: 0.0,voteCount ?: 0),
//    genres = genreIds.orEmpty().map { LocalGenre(GenreType.fromApi(it ?: 0)) }
    genres = genreIds.orEmpty().map { LocalGenre(it.toString()) }
)


