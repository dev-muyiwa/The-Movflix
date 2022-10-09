package com.devmuyiwa.themovflix.popularmovie.domain.usecases

import com.devmuyiwa.themovflix.common.domain.Resource
import com.devmuyiwa.themovflix.common.domain.model.PopularMovie
import com.devmuyiwa.themovflix.common.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchPopularMovie @Inject constructor(
    private val repository: MoviesRepository,
    private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(pageToLoad: Int): Flow<Resource<List<PopularMovie>>> =
        withContext(ioDispatcher) {
            repository.fetchPopularMoviesStream(pageToLoad)
        }
}