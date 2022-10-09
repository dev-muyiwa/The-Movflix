package com.devmuyiwa.themovflix.common.data.local.dao

import androidx.room.*
import com.devmuyiwa.themovflix.common.data.local.model.LocalMovie
import com.devmuyiwa.themovflix.common.data.local.model.LocalMovieAggregate
import com.devmuyiwa.themovflix.common.data.local.model.genre.LocalGenre
import com.devmuyiwa.themovflix.common.data.local.model.popularmovie.LocalPopularMovie
import com.devmuyiwa.themovflix.common.data.local.model.popularmovie.PopularMovieWithGenre

@Dao
interface MoviesDao {
    @Transaction
    @Query("SELECT * FROM ${LocalPopularMovie.LOCAL_POPULAR_MOVIE} ORDER BY popularity DESC")
    suspend fun fetchLocalPopularMoviesStream(): List<PopularMovieWithGenre>

    @Transaction
    @Query("SELECT * FROM ${LocalMovie.LOCAL_MOVIE} WHERE movieId = :id")
    fun fetchLocalMovieInfo(id: Long): LocalMovieAggregate

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMoviesAggregate(
        movie: LocalPopularMovie,
        genres: List<LocalGenre>,
    )

    suspend fun insertPopularMovies(popularMovies: List<PopularMovieWithGenre>) {
        for (movie in popularMovies) {
            insertPopularMoviesAggregate(
                movie = movie.popularMovie,
                genres = movie.genres
            )
        }
    }

    @Query("DELETE FROM ${LocalPopularMovie.LOCAL_POPULAR_MOVIE} WHERE movieId IN (:movieIds)")
    suspend fun deletePopularMovies(movieIds: List<Long>)
}