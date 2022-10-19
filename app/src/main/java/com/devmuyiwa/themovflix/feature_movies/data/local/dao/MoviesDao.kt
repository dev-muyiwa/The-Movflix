package com.devmuyiwa.themovflix.feature_movies.data.local.dao

import androidx.room.*
import com.devmuyiwa.themovflix.feature_movies.data.local.model.LocalMovieWithCategory
import com.devmuyiwa.themovflix.feature_movies.data.local.model.LocalMovieWithDetails
import com.devmuyiwa.themovflix.feature_movies.data.local.model.company.LocalMovieCompanyCrossRef
import com.devmuyiwa.themovflix.feature_movies.data.local.model.company.LocalProdCompany
import com.devmuyiwa.themovflix.feature_movies.data.local.model.country.LocalMovieCountryCrossRef
import com.devmuyiwa.themovflix.feature_movies.data.local.model.country.LocalProdCountry
import com.devmuyiwa.themovflix.feature_movies.data.local.model.details.LocalDetails
import com.devmuyiwa.themovflix.feature_movies.data.local.model.genre.LocalGenre
import com.devmuyiwa.themovflix.feature_movies.data.local.model.genre.LocalMovieGenreCrossRef
import com.devmuyiwa.themovflix.feature_movies.data.local.model.movie.LocalMovie
import com.devmuyiwa.themovflix.feature_movies.utils.Category

@Dao
abstract class MoviesDao {
    /** Movies by Category. */
    @Transaction
    @Query("SELECT * FROM ${LocalMovie.LOCAL_MOVIE}" +
            " WHERE category = :category ORDER BY popularity DESC")
    abstract suspend fun fetchMoviesByCategory(category: String): List<LocalMovieWithCategory>

    suspend fun fetchCategorisedMoviesStream(category: Category): List<LocalMovieWithCategory> =
        fetchMoviesByCategory(category.name)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCategorisedMovieAggregate(
        movie: LocalMovie,
        genre: List<LocalGenre>,
    )

    suspend fun insertCategorisedMovies(movies: List<LocalMovieWithCategory>) {
        movies.forEach {
            insertCategorisedMovieAggregate(it.movie, it.genres)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMovieWithDetails(
        movie: LocalMovie,
        details: LocalDetails,
        genre: List<LocalGenre>,
        prodCompany: List<LocalProdCompany>,
        prodCountry: List<LocalProdCountry>
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertGenreCrossRef(join: LocalMovieGenreCrossRef): Long

    @Query("DELETE FROM ${LocalMovie.LOCAL_MOVIE} WHERE movieId IN (:movieIds)")
    abstract suspend fun deleteMovies(movieIds: List<Long>)


    /** Movie Details.*/

    @Transaction
    @Query("SELECT * FROM ${LocalMovie.LOCAL_MOVIE} WHERE movieId = :movieId")
    abstract suspend fun fetchMovieDetails(movieId: Long): LocalMovieWithDetails

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertProdCompanyCrossRef(join: LocalMovieCompanyCrossRef): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertProdCountryCrossRef(join: LocalMovieCountryCrossRef): Long

    @Query("DELETE FROM ${LocalDetails.LOCAL_DETAILS} WHERE movieId = :movieId")
    abstract suspend fun deleteMovieWithDetails(movieId: Long)
}