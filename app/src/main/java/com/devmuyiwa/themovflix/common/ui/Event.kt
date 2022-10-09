package com.devmuyiwa.themovflix.common.ui

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