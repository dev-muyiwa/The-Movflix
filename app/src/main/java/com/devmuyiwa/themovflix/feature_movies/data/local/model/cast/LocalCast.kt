package com.devmuyiwa.themovflix.feature_movies.data.local.model.cast

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devmuyiwa.themovflix.feature_movies.domain.model.details.Cast

@Entity(tableName = LocalCast.LOCAL_CAST)
data class LocalCast(
    @PrimaryKey val castName: String = "",
    val profileUrl: String = "",
    val character: String = "",
) {
    companion object {
        const val LOCAL_CAST = "cast"
    }
}

fun LocalCast.asDomainModel() = Cast(
    name = castName,
    profileUrl = profileUrl,
    character = character
)
