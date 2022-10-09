package com.devmuyiwa.themovflix.common.data.local.model.popularmovie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LocalPopularMovie.LOCAL_POPULAR_MOVIE)
data class LocalPopularMovie(
    @PrimaryKey(autoGenerate = false) val movieId: Long = 0,
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
) {
    companion object {
        const val LOCAL_POPULAR_MOVIE = "popular_movie"
    }
}
