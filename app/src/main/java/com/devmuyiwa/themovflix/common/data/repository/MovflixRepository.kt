package com.devmuyiwa.themovflix.common.data.repository

import com.devmuyiwa.themovflix.common.data.local.dao.MoviesDao
import com.devmuyiwa.themovflix.common.data.local.model.popularmovie.asDomainModel
import com.devmuyiwa.themovflix.common.data.remote.MovflixApi
import com.devmuyiwa.themovflix.common.data.remote.model.popularmovie.asEntityModel
import com.devmuyiwa.themovflix.common.domain.Resource
import com.devmuyiwa.themovflix.common.domain.model.PopularMovie
import com.devmuyiwa.themovflix.common.domain.repository.MoviesRepository
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
    override fun fetchPopularMoviesStream(
        pageToLoad: Int,
    ): Flow<Resource<List<PopularMovie>>> = flow {
        emit(Resource.Loading())
        val popularMovies = dao.fetchLocalPopularMoviesStream().map { popularMovies ->
            popularMovies.asDomainModel()
        }
        emit(Resource.Loading(data = popularMovies))
        try {
            val remotePopularMovies = api.fetchRemotePopularMovies(language, pageToLoad).movies
            dao.deletePopularMovies(remotePopularMovies.orEmpty().map { it!!.asEntityModel()
                .popularMovie
                .movieId })
            dao.insertPopularMovies(remotePopularMovies.orEmpty().map { it!!.asEntityModel() })
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, Something went wrong!",
                data = popularMovies))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Server is currently unreachable. Check your internet connection.",
                data = popularMovies))
        }
        val newPopularMovie = dao.fetchLocalPopularMoviesStream().map { it.asDomainModel() }
        emit(Resource.Success(newPopularMovie))
    }

}