package com.devmuyiwa.themovflix.feature_movies.data.local.model.genre

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LocalGenre.LOCAL_GENRE)
data class LocalGenre(
    @PrimaryKey val genreId: Int = 0,
    val genre: String = ""
) {
    companion object {
        const val LOCAL_GENRE = "genre"
    }
}

fun LocalGenre.asDomainModel() = genre
