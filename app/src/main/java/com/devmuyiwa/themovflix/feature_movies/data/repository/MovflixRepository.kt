package com.devmuyiwa.themovflix.feature_movies.data.repository

import android.util.Log
import com.devmuyiwa.themovflix.core.util.Resource
import com.devmuyiwa.themovflix.feature_movies.data.local.dao.MoviesDao
import com.devmuyiwa.themovflix.feature_movies.data.local.model.LocalMovieWithCategory
import com.devmuyiwa.themovflix.feature_movies.data.local.model.asDomainModel
import com.devmuyiwa.themovflix.feature_movies.data.local.model.company.LocalMovieCompanyCrossRef
import com.devmuyiwa.themovflix.feature_movies.data.local.model.country.LocalMovieCountryCrossRef
import com.devmuyiwa.themovflix.feature_movies.data.local.model.genre.LocalGenre
import com.devmuyiwa.themovflix.feature_movies.data.local.model.genre.LocalMovieGenreCrossRef
import com.devmuyiwa.themovflix.feature_movies.data.remote.MovflixApi
import com.devmuyiwa.themovflix.feature_movies.data.remote.dto.category.RemoteCategorisedMovie
import com.devmuyiwa.themovflix.feature_movies.data.remote.dto.category.asEntityModel
import com.devmuyiwa.themovflix.feature_movies.data.remote.dto.movieinfo.*
import com.devmuyiwa.themovflix.feature_movies.domain.model.*
import com.devmuyiwa.themovflix.feature_movies.domain.repository.MoviesRepository
import com.devmuyiwa.themovflix.feature_movies.utils.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovflixRepository @Inject constructor(
    private val api: MovflixApi,
    private val dao: MoviesDao,
) : MoviesRepository {
    private val language = "en-US"
    override fun fetchCategorisedMoviesStream(pageToLoad: Int, category: Category)
            : Flow<Resource<List<CategorisedMovie>>> {
        return flow {
            emit(Resource.Loading())
            val localCategorisedMovies = dao.fetchCategorisedMoviesStream(category)
                .map { it.asDomainModel() }
            emit(Resource.Loading(data = localCategorisedMovies))
            try {
                val movies: List<RemoteCategorisedMovie?>? = when (category) {
                    Category.POPULAR -> {
                        api.fetchPopularMovies(language, pageToLoad).movies
                    }
                    Category.NOW_PLAYING -> {
                        api.fetchNowPlayingMovies(language, pageToLoad).movies
                    }
                    Category.TOP_RATED -> {
                        api.fetchTopRatedMovies(language, pageToLoad).movies
                    }
                    Category.RECOMMENDED -> {
                        api.fetchRecommendedMovies(language, pageToLoad).movies
                    }
                    Category.UPCOMING -> {
                        api.fetchUpcomingMovies(language, pageToLoad).movies
                    }
                }
                dao.deleteMovies(movies.orEmpty().map { it!!.asEntityModel(category).movieId })
                dao.insertCategorisedMovies(
                    movies = movies.orEmpty().map { movie ->
                        LocalMovieWithCategory(
                            movie = movie!!.asEntityModel(category),
                            genres = movie.genreIds.orEmpty().map { id ->
                                LocalGenre(id ?: 0, GenreType.fromApi(id ?: 0))
                            })
                    })
                movies.orEmpty().forEach { movie ->
                    movie?.genreIds.orEmpty().forEach { id ->
                        dao.insertGenreCrossRef(LocalMovieGenreCrossRef(
                            movie?.movieId ?: 0, id ?: 0
                        ))
                    }
                }
            } catch (e: HttpException) {
                emit(Resource.Error(
                    message = e.localizedMessage ?: "Oops, Something went wrong!",
                    data = localCategorisedMovies))
            } catch (e: IOException) {
                emit(Resource.Error(
                    message = "Server is currently unreachable. Check your internet connection.",
                    data = localCategorisedMovies))
            }
            val newMovies = dao.fetchCategorisedMoviesStream(category).map { it.asDomainModel() }
            emit(Resource.Success(data = newMovies))
        }
    }

    override fun fetchMovieInfo(movieId: Long): Flow<Resource<MovieWithDetails>> {
        return flow {
            emit(Resource.Loading())
            val localMovieDetails = dao.fetchMovieDetails(movieId).asDomainModel()
            Log.d("Movie Details", "$localMovieDetails")
            emit(Resource.Loading(localMovieDetails))
            try {
                val remoteMovieDetails: RemoteMovieInfo = api.fetchRemoteMovieInfoById(movieId)
                dao.deleteMovieWithDetails(movieId)
                dao.insertMovieWithDetails(
                    movie = remoteMovieDetails.asEntityMovie(),
                    details = remoteMovieDetails.asEntityDetail(),
                    genre = remoteMovieDetails.genres.orEmpty().map { genre ->
                        LocalGenre(genre?.genreId ?: 0, genre?.genreName.orEmpty())
                    },
                    prodCompany = remoteMovieDetails.prodCompanies.orEmpty().map { company ->
                        company!!.asEntityModel()
                    },
                    prodCountry = remoteMovieDetails.prodCountries.orEmpty().map { country ->
                        country!!.asEntityModel()
                    }
                )
                remoteMovieDetails.genres.orEmpty().forEach { genre ->
                    dao.insertGenreCrossRef(LocalMovieGenreCrossRef(
                        movieId = remoteMovieDetails.movieId ?: 0, genreId = genre?.genreId ?: 0
                    ))
                }
                remoteMovieDetails.prodCompanies.orEmpty().forEach { company ->
                    dao.insertProdCompanyCrossRef(LocalMovieCompanyCrossRef(
                        movieId = movieId, companyId = company?.companyId ?: 0
                    ))
                }
                remoteMovieDetails.prodCountries.orEmpty().forEach { country ->
                    dao.insertProdCountryCrossRef(LocalMovieCountryCrossRef(
                        movieId = movieId, isoValue = country?.isoValue.orEmpty()
                    ))
                }
            } catch (e: HttpException) {
                emit(Resource.Error(
                    message = "Oops, Something went wrong!",
                    data = localMovieDetails))
            } catch (e: IOException) {
                emit(Resource.Error(
                    message = "Server is currently unreachable. Check your internet connection.",
                    data = localMovieDetails))
            }
            val newLocalMovieWithDetails = dao.fetchMovieDetails(movieId).asDomainModel()
            emit(Resource.Success(data = newLocalMovieWithDetails))
        }
    }
}
