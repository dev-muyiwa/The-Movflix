package com.devmuyiwa.themovflix.feature_movies.data.local.model

import androidx.room.*
import com.devmuyiwa.themovflix.feature_movies.data.local.model.genre.LocalGenre
import com.devmuyiwa.themovflix.feature_movies.data.local.model.genre.LocalMovieGenreCrossRef
import com.devmuyiwa.themovflix.feature_movies.data.local.model.movie.LocalMovie
import com.devmuyiwa.themovflix.feature_movies.data.local.model.movie.asDomainModel
import com.devmuyiwa.themovflix.feature_movies.domain.model.CategorisedMovie

data class LocalMovieWithCategory(
    @Embedded val movie: LocalMovie,
    @Relation(
        entity = LocalGenre::class,
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(
            value = LocalMovieGenreCrossRef::class,
            parentColumn = "movieId",
            entityColumn = "genreId"
        )
    ) val genres: List<LocalGenre>,
)

fun LocalMovieWithCategory.asDomainModel() = CategorisedMovie(
    movie = movie.asDomainModel(),
    genres = genres.map { it.genre },
    category = movie.category
)
