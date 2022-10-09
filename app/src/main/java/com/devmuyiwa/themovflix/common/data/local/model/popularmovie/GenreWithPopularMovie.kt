package com.devmuyiwa.themovflix.common.data.local.model.popularmovie

import androidx.room.*
import com.devmuyiwa.themovflix.common.data.local.model.genre.LocalGenre
import com.devmuyiwa.themovflix.common.data.local.model.genre.LocalMovieGenreCrossRef

data class GenreWithPopularMovie(
    @Embedded val genre: LocalGenre,
    @Relation(
        parentColumn = "genre",
        entityColumn = "movieId",
        associateBy = Junction(LocalMovieGenreCrossRef::class)
    ) val popularMovie: List<LocalPopularMovie>,
)