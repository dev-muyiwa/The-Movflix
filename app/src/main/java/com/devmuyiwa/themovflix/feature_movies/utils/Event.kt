package com.devmuyiwa.themovflix.feature_movies.utils

class Event<out T>(private val content: T) {
    private var isHandled = false
    fun getContent(): T? {
        return if (isHandled) null
        else {
            isHandled = true
            content
        }
    }
}