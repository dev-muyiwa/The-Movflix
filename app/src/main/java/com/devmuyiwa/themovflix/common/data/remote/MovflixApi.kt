package com.devmuyiwa.themovflix.common.data.remote

import com.devmuyiwa.themovflix.common.data.remote.model.movieinfo.RemoteMovieInfo
import com.devmuyiwa.themovflix.common.data.remote.model.popularmovie.RemotePopularMovieDto
import com.devmuyiwa.themovflix.common.data.remote.util.*
import retrofit2.http.*

interface MovflixApi {
    @GET(POPULAR_MOVIE_ENDPOINT)
    suspend fun fetchPopularMovies(
        @Query(LANG) language: String,
        @Query(PAGE) pageToLoad: Int,
    ): RemotePopularMovieDto

    @GET(MOVIE_INFO)
    suspend fun fetchRemoteMovieInfo(
        @Path(MOVIE_ID) id: Long,
    ): RemoteMovieInfo
}