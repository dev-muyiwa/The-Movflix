package com.devmuyiwa.themovflix.common.data.local.model.popularmovie

import androidx.room.*
import com.devmuyiwa.themovflix.common.data.local.model.genre.*
import com.devmuyiwa.themovflix.common.domain.model.PopularMovie

data class PopularMovieWithGenre(
    @Embedded val popularMovie: LocalPopularMovie,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "genre",
        associateBy = Junction(LocalMovieGenreCrossRef::class)
    ) val genres: List<LocalGenre>,
)

fun PopularMovieWithGenre.asDomainModel() = PopularMovie(
    movieId = popularMovie.movieId,
    isAdult = popularMovie.isAdult,
    backdropUrl = popularMovie.backdropUrl,
    genres = genres.map { it.asDomainModel() },
    originalLanguage = popularMovie.originalLanguage,
    originalTitle = popularMovie.originalTitle,
    overview = popularMovie.overview,
    popularity = popularMovie.popularity,
    posterUrl = popularMovie.posterUrl,
    releaseDate = popularMovie.releaseDate,
    title = popularMovie.title,
    averageVote = popularMovie.averageVote,
    voteCount = popularMovie.voteCount
)
