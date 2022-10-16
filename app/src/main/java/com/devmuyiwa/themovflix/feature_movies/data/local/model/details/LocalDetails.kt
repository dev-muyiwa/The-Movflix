package com.devmuyiwa.themovflix.feature_movies.data.local.model.details

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.devmuyiwa.themovflix.feature_movies.domain.model.Details

@Entity(tableName = LocalDetails.LOCAL_DETAILS)
data class LocalDetails(
    @PrimaryKey val budget: Long = 0,
    val movieId: Long = 0,
    val homepageUrl: String = "",
    val revenue: Long = 0,
    val runtime: Int = 0,
    val status: String = "",
    val tagline: String = ""
){
    companion object {
        const val LOCAL_DETAILS = "details"
    }
}

fun LocalDetails.asDomainModel() = Details(
    budget = budget,
    homepageUrl = homepageUrl,
    revenue = revenue,
    runtime = runtime,
    status = status,
    tagline = tagline,
    genre = listOf(),
    prodCompany = listOf(),
    prodCountry = listOf()
)
