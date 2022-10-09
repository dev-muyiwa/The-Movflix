package com.devmuyiwa.themovflix.popularmovie

import com.devmuyiwa.themovflix.common.data.local.dao.MoviesDao
import com.devmuyiwa.themovflix.common.data.remote.MovflixApi
import com.devmuyiwa.themovflix.common.data.repository.MovflixRepository
import com.devmuyiwa.themovflix.common.domain.repository.MoviesRepository
import com.devmuyiwa.themovflix.popularmovie.domain.usecases.FetchPopularMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PopularMoviesModule {
    @Provides
    @Singleton
    fun provideFetchPopularMoviesUseCase(
        repository: MoviesRepository,
        ioDispatcher: CoroutineDispatcher,
    ): FetchPopularMovie = FetchPopularMovie(repository, ioDispatcher)

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