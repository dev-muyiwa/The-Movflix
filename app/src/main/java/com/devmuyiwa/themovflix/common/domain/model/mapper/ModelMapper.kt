package com.devmuyiwa.themovflix.common.domain.model.mapper

interface ModelMapper< E, D> {
    fun map(model: E): D
}