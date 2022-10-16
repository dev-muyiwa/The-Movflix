package com.devmuyiwa.themovflix.core.util

import java.io.IOException

class NetworkUnavailableException(message: String = "Server is currently unreachable. Check your internet connection.") : IOException(message)
