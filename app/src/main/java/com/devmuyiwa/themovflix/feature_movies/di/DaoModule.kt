package com.devmuyiwa.themovflix.feature_movies.di

import com.devmuyiwa.themovflix.feature_movies.data.local.MovflixDatabase
import com.devmuyiwa.themovflix.feature_movies.data.local.dao.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideMoviesDao(db: MovflixDatabase): MoviesDao = db.fetchMoviesDao()

}