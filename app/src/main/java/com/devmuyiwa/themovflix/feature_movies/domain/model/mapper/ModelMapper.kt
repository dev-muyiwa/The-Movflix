package com.devmuyiwa.themovflix.feature_movies.domain.model.mapper

interface ModelMapper< E, D> {
    fun map(model: E): D
}