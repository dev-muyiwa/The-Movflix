package com.devmuyiwa.themovflix.common.data.local.model.genre

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LocalGenre.LOCAL_GENRE)
data class LocalGenre(
    @PrimaryKey(autoGenerate = false) val genre: String = "",
) {
    companion object {
        const val LOCAL_GENRE = "genre"
    }
}

fun LocalGenre.asDomainModel() = genre