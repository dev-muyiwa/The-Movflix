package com.devmuyiwa.themovflix.common.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LocalMovie.LOCAL_MOVIE)
data class LocalMovie(
    @PrimaryKey val movieId: Long = 0,
    val adult: Boolean = false,
    val backdropUrl: String = "",
    val budget: Long = 0,
    val homepageUrl: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterUrl: String = "",
    val releaseDate: String = "",
    val revenue: Long = 0,
    val runtime: Int = 0,
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val averageVote: Double = 0.0,
    val voteCount: Long = 0,

    ) {
    companion object {
        const val LOCAL_MOVIE = "movie"
    }
}


