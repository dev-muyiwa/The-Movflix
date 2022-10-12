package com.devmuyiwa.themovflix.common.data.local.dao

import androidx.room.*
import com.devmuyiwa.themovflix.common.data.local.model.LocalMovieWithCategory
import com.devmuyiwa.themovflix.common.data.local.model.genre.LocalGenre
import com.devmuyiwa.themovflix.common.data.local.model.movie.LocalMovie
import com.devmuyiwa.themovflix.common.domain.Category

@Dao
abstract class MoviesDao {

    @Transaction
    @Query("SELECT * FROM ${LocalMovie.LOCAL_MOVIE}" +
            " WHERE category = :category ORDER BY popularity DESC")
    abstract suspend fun fetchMoviesByCategory(category: String): List<LocalMovieWithCategory>

    suspend fun fetchPopularMoviesStream(): List<LocalMovieWithCategory> =
        fetchMoviesByCategory(Category.POPULAR.name)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertPopularMoviesAggregate(
        genres: List<LocalGenre>,
        movie: LocalMovie,
    )

    suspend fun insertPopularMovies(popularMovies: List<LocalMovieWithCategory>) {
        popularMovies.forEach {
            insertPopularMoviesAggregate( it.genres, it.movie)
        }
    }

    @Query("DELETE FROM ${LocalMovie.LOCAL_MOVIE} WHERE movieId IN (:movieIds)")
    abstract suspend fun deletePopularMovies(movieIds: List<Long>)
}