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
    // Movies by Category
    @Transaction
    @Query("SELECT * FROM ${LocalMovie.LOCAL_MOVIE}" +
            " WHERE category = :category ORDER BY popularity DESC")
    abstract suspend fun fetchMoviesByCategory(category: String): List<LocalMovieWithCategory>

    suspend fun fetchCategorisedMoviesStream(category: Category): List<LocalMovieWithCategory> =
        fetchMoviesByCategory(category.name)

    @Transaction
    @Query("SELECT * FROM ${LocalMovie.LOCAL_MOVIE} WHERE movieId = :movieId")
    abstract suspend fun fetchMovieDetails(movieId: Long): LocalMovieWithDetails

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMovie(movie: LocalMovie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertGenre(genre: LocalGenre): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertDetails(details: LocalDetails): Long

    // 1
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPopularMovieAggregate(
        movie: LocalMovie,
        genre: List<LocalGenre>,
    )

    // 2
    suspend fun insertCategorisedMovies(popularMovies: List<LocalMovieWithCategory>) {
        popularMovies.forEach {
            insertPopularMovieAggregate(it.movie, it.genres)
        }
    }

    suspend fun insertMovieDetails(movieDetails: LocalMovieWithDetails) {
        insertMovie(movieDetails.movie)
        insertDetails(movieDetails.details)
        movieDetails.genres.forEach { insertGenre(it) }
        movieDetails.productionCompanies.forEach { insertProdCompany(it) }
        movieDetails.productionCountries.forEach { insertProdCountry(it) }
        TODO("Add List of Casts.")
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertProdCompany(company: LocalProdCompany): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertProdCountry(country: LocalProdCountry): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertGenreCrossRef(join: LocalMovieGenreCrossRef): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertProdCompanyCrossRef(join: LocalMovieCompanyCrossRef): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertProdCountryCrossRef(join: LocalMovieCountryCrossRef): Long

    @Query("DELETE FROM ${LocalMovie.LOCAL_MOVIE} WHERE movieId IN (:movieIds)")
    abstract suspend fun deleteMovies(movieIds: List<Long>)

    @Query("DELETE FROM ${LocalDetails.LOCAL_DETAILS} WHERE movieId = :movieId")
    abstract suspend fun deleteMovieInfo(movieId: Long)

    // Movie Details

}