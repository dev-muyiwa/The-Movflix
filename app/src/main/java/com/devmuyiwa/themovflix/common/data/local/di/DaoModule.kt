package com.devmuyiwa.themovflix.common.data.local.di

import com.devmuyiwa.themovflix.common.data.local.MovflixDatabase
import com.devmuyiwa.themovflix.common.data.local.dao.MoviesDao
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