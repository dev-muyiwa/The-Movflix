package com.devmuyiwa.themovflix.feature_movies.data.remote.dto.category


import com.devmuyiwa.themovflix.feature_movies.data.local.model.movie.LocalMovie
import com.devmuyiwa.themovflix.feature_movies.data.remote.util.*
import com.devmuyiwa.themovflix.feature_movies.utils.Category
import com.devmuyiwa.themovflix.feature_movies.domain.model.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteCategorisedMovie(
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
/*
fun RemoteCategorisedMovie.asDomainModel(category: Category) = CategorisedMovie(
    movie = mapLocalMovie(this),
//    genres = genreIds.orEmpty().map { GenreType.fromApi(it ?: 0) },
    genres = genreIds.orEmpty().map { it.toString() },
    category = category.name
    /*** The category is added to avoid duplication of similar api entities. **/
)
 */

private fun mapLocalMovie(entity: RemoteCategorisedMovie): Movie = Movie(
    movieId = entity.movieId ?: 0,
    isAdult = entity.isAdult ?: false,
    backdropUrl = IMAGE_BASE_ENDPOINT + BACKDROP_SIZE + entity.backdropPath.orEmpty(),
    originalLanguage = entity.originalLanguage.orEmpty(),
    originalTitle = entity.originalTitle.orEmpty(),
    overview = entity.overview.orEmpty(),
    popularity = entity.popularity ?: 0.0,
    posterUrl = IMAGE_BASE_ENDPOINT + POSTER_SIZE + entity.posterPath.orEmpty(),
    releaseDate = entity.releaseDate.orEmpty(),
    title = entity.title.orEmpty(),
    averageVote = entity.averageVote ?: 0.0,
    voteCount = entity.voteCount ?: 0
)

fun RemoteCategorisedMovie.asEntityModel(category: Category) = LocalMovie(
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
    voteCount = voteCount ?: 0,
    /*** The category is added to avoid duplication of similar api entities. **/
    category = category.name
)

