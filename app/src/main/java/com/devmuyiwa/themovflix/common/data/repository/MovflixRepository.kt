package com.devmuyiwa.themovflix.common.data.repository

import android.util.Log
import com.devmuyiwa.themovflix.common.data.local.dao.MoviesDao
import com.devmuyiwa.themovflix.common.data.local.model.asDomainModel
import com.devmuyiwa.themovflix.common.data.remote.MovflixApi
import com.devmuyiwa.themovflix.common.data.remote.model.popularmovie.asEntityModel
import com.devmuyiwa.themovflix.common.domain.Resource
import com.devmuyiwa.themovflix.common.domain.model.CategorisedMovie
import com.devmuyiwa.themovflix.common.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.*
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
    ): Flow<Resource<List<CategorisedMovie>>> = flow {
        emit(Resource.Loading())
        val popularMovies = dao.fetchPopularMoviesStream().map { it.asDomainModel() }
        emit(Resource.Loading(data = popularMovies))
        try {
            val remotePopularMovies = api.fetchPopularMovies(language, pageToLoad).movies
            Log.i("Remote Genre", "$remotePopularMovies") // 1
            dao.deletePopularMovies(remotePopularMovies.orEmpty()
                .map { it!!.asEntityModel().movie.movieId })
            dao.insertPopularMovies(remotePopularMovies.orEmpty()
                .map { it!!.asEntityModel() })
            Log.d("Local Genre", "${remotePopularMovies.orEmpty()
                .map { it!!.asEntityModel() }}") // 2
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, Something went wrong!",
                data = popularMovies))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Server is currently unreachable. Check your internet connection.",
                data = popularMovies))
        }
        val newPopularMovie = dao.fetchPopularMoviesStream().map { it.asDomainModel() }
//        Log.d("Local Genre", "$newPopularMovie")
        emit(Resource.Success(newPopularMovie))
    }

}