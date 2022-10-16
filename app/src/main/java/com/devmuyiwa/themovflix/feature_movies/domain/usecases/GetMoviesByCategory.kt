package com.devmuyiwa.themovflix.feature_movies.domain.usecases

import com.devmuyiwa.themovflix.feature_movies.utils.Category
import com.devmuyiwa.themovflix.core.util.Resource
import com.devmuyiwa.themovflix.feature_movies.domain.model.CategorisedMovie
import com.devmuyiwa.themovflix.feature_movies.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMoviesByCategory @Inject constructor(
    private val repository: MoviesRepository,
    private val ioDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(pageToLoad: Int, category: Category)
            : Flow<Resource<List<CategorisedMovie>>> {
        return withContext(ioDispatcher) {
            repository.fetchCategorisedMoviesStream(pageToLoad, category)
        }
    }
}