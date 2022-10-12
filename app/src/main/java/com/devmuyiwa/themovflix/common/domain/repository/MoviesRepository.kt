package com.devmuyiwa.themovflix.common.domain.repository

import com.devmuyiwa.themovflix.common.domain.Resource
import com.devmuyiwa.themovflix.common.domain.model.*
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun fetchPopularMoviesStream(pageToLoad: Int): Flow<Resource<List<CategorisedMovie>>>
//    suspend fun fetchRemotePopularMovies(lang: String, pageToLoad: Int): PaginatedPopularMovie
//    fun fetchLocalMovie(id: Long): Movie
//    suspend fun fetchRemoteMovie(id: Long): Movie
}