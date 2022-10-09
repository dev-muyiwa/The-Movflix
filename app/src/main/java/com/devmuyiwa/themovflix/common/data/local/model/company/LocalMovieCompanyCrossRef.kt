package com.devmuyiwa.themovflix.common.data.local.model.company

import androidx.room.Entity
import androidx.room.Index

@Entity(primaryKeys = ["movieId", "companyId"], indices = [Index("companyId")])
data class LocalMovieCompanyCrossRef(
    val movieId: Long = 0,
    val companyId: Long = 0,
)
