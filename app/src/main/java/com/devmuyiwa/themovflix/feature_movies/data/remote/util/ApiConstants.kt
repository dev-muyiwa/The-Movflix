package com.devmuyiwa.themovflix.feature_movies.data.remote.util

/** API Endpoints.*/
const val BASE_ENDPOINT = "https://api.themoviedb.org/3/"
const val POPULAR_MOVIE_ENDPOINT = "movie/popular"
const val NOW_PLAYING_ENDPOINT = "movie/now_playing"
const val TOP_RATED_ENDPOINT = "movie/top_rated"
const val RECOMMENDED_ENDPOINT = "movie/recommended"
const val UPCOMING_ENDPOINT = "movie/upcoming"
const val IMAGE_BASE_ENDPOINT = "https://image.tmdb.org/t/p/"
const val MOVIE_ID = "movieId"
const val MOVIE_INFO = "movie/{$MOVIE_ID}"
const val KEY_NAME = "api_key"

/** Image Sizes. */
const val PROFILE_SIZE = "w185"
const val POSTER_SIZE = "w185"
const val BACKDROP_SIZE = "w780"

/**API Parameters.*/
const val PAGE = "page"
const val LANG = "language"
