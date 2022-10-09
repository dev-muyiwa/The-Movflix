package com.devmuyiwa.themovflix.common.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devmuyiwa.themovflix.common.data.local.dao.MoviesDao
import com.devmuyiwa.themovflix.common.data.local.model.LocalMovie
import com.devmuyiwa.themovflix.common.data.local.model.cast.LocalCast
import com.devmuyiwa.themovflix.common.data.local.model.cast.LocalMovieCastCrossRef
import com.devmuyiwa.themovflix.common.data.local.model.company.LocalMovieCompanyCrossRef
import com.devmuyiwa.themovflix.common.data.local.model.company.LocalProdCompany
import com.devmuyiwa.themovflix.common.data.local.model.country.LocalMovieCountryCrossRef
import com.devmuyiwa.themovflix.common.data.local.model.country.LocalProdCountry
import com.devmuyiwa.themovflix.common.data.local.model.genre.LocalGenre
import com.devmuyiwa.themovflix.common.data.local.model.genre.LocalMovieGenreCrossRef
import com.devmuyiwa.themovflix.common.data.local.model.popularmovie.LocalPopularMovie

@Database(
    entities = [
        LocalMovie::class,
        LocalCast::class,
        LocalProdCountry::class,
        LocalProdCompany::class,
        LocalGenre::class,
        LocalPopularMovie::class,
        LocalMovieCastCrossRef::class,
        LocalMovieCompanyCrossRef::class,
        LocalMovieCountryCrossRef::class,
        LocalMovieGenreCrossRef::class
    ],
    version = 1, exportSchema = true)
abstract class MovflixDatabase : RoomDatabase() {
    abstract fun fetchMoviesDao(): MoviesDao
}