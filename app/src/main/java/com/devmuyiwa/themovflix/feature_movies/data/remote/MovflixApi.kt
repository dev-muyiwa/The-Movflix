package com.devmuyiwa.themovflix.feature_movies.data.remote

import com.devmuyiwa.themovflix.feature_movies.data.remote.dto.movieinfo.RemoteMovieInfo
import com.devmuyiwa.themovflix.feature_movies.data.remote.dto.category.RemoteCategorisedMovieDto
import com.devmuyiwa.themovflix.feature_movies.data.remote.util.*
import retrofit2.http.*

interface MovflixApi {
    @GET(POPULAR_MOVIE_ENDPOINT)
    suspend fun fetchPopularMovies(
        @Query(LANG) language: String,
        @Query(PAGE) pageToLoad: Int,
    ): RemoteCategorisedMovieDto

    @GET(NOW_PLAYING_ENDPOINT)
    suspend fun fetchNowPlayingMovies(
        @Query(LANG) language: String,
        @Query(PAGE) pageToLoad: Int,
    ): RemoteCategorisedMovieDto

    @GET(TOP_RATED_ENDPOINT)
    suspend fun fetchTopRatedMovies(
        @Query(LANG) language: String,
        @Query(PAGE) pageToLoad: Int,
    ): RemoteCategorisedMovieDto

    @GET(RECOMMENDED_ENDPOINT)
    suspend fun fetchRecommendedMovies(
        @Query(LANG) language: String,
        @Query(PAGE) pageToLoad: Int,
    ): RemoteCategorisedMovieDto

    @GET(UPCOMING_ENDPOINT)
    suspend fun fetchUpcomingMovies(
        @Query(LANG) language: String,
        @Query(PAGE) pageToLoad: Int,
    ): RemoteCategorisedMovieDto

    @GET(MOVIE_INFO)
    suspend fun fetchRemoteMovieInfoById(
        @Path(MOVIE_ID) id: Long,
    ): RemoteMovieInfo
}