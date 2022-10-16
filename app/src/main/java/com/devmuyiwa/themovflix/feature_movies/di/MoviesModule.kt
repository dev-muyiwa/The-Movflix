package com.devmuyiwa.themovflix.feature_movies.di

import com.devmuyiwa.themovflix.feature_movies.data.local.dao.MoviesDao
import com.devmuyiwa.themovflix.feature_movies.data.remote.MovflixApi
import com.devmuyiwa.themovflix.feature_movies.data.repository.MovflixRepository
import com.devmuyiwa.themovflix.feature_movies.domain.repository.MoviesRepository
import com.devmuyiwa.themovflix.feature_movies.domain.usecases.GetMovieDetail
import com.devmuyiwa.themovflix.feature_movies.domain.usecases.GetMoviesByCategory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {
    @Provides
    @Singleton
    fun provideFetchCategorisedMoviesUseCase(
        repository: MoviesRepository,
        ioDispatcher: CoroutineDispatcher,
    ): GetMoviesByCategory = GetMoviesByCategory(repository, ioDispatcher)

    @Provides
    @Singleton
    fun provideGetMovieDetails(
        repository: MoviesRepository,
        ioDispatcher: CoroutineDispatcher
    ): GetMovieDetail = GetMovieDetail(repository, ioDispatcher)

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: MovflixApi,
        dao: MoviesDao,
    ): MoviesRepository = MovflixRepository(api, dao)

    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}