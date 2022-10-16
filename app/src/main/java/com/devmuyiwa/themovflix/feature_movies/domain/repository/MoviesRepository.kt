package com.devmuyiwa.themovflix.feature_movies.domain.repository

import com.devmuyiwa.themovflix.feature_movies.utils.Category
import com.devmuyiwa.themovflix.core.util.Resource
import com.devmuyiwa.themovflix.feature_movies.domain.model.CategorisedMovie
import com.devmuyiwa.themovflix.feature_movies.domain.model.MovieWithDetails
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun fetchCategorisedMoviesStream(pageToLoad: Int, category: Category):
            Flow<Resource<List<CategorisedMovie>>>
    fun fetchMovieInfo(movieId: Long): Flow<Resource<MovieWithDetails>>
}