package com.devmuyiwa.themovflix.common.data.local.model.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devmuyiwa.themovflix.common.data.local.model.genre.asDomainModel
import com.devmuyiwa.themovflix.common.domain.model.Movie

@Entity(tableName = LocalMovie.LOCAL_MOVIE)
data class LocalMovie(
    @PrimaryKey val movieId: Long = 0,
    val isAdult: Boolean = false,
    val backdropUrl: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterUrl: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val averageVote: Double = 0.0,
    val voteCount: Long = 0,
    val category: String = ""
    ) {
    companion object {
        const val LOCAL_MOVIE = "movie"
    }
}

fun LocalMovie.asDomainModel() = Movie(
    movieId = movieId,
    isAdult = isAdult,
    backdropUrl = backdropUrl,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterUrl = posterUrl,
    releaseDate = releaseDate,
    title = title,
    averageVote = averageVote,
    voteCount = voteCount
)