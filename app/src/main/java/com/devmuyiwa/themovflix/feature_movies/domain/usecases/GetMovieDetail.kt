package com.devmuyiwa.themovflix.feature_movies.domain.usecases

import com.devmuyiwa.themovflix.core.util.Resource
import com.devmuyiwa.themovflix.feature_movies.domain.model.MovieWithDetails
import com.devmuyiwa.themovflix.feature_movies.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieDetail @Inject constructor(
    private val repository: MoviesRepository,
    private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(movieId: Long): Flow<Resource<MovieWithDetails>> =
        withContext(ioDispatcher){
            repository.fetchMovieInfo(movieId)
        }
}