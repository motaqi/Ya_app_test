package com.yassir.yassirapptest.util

object NetworkConstant {
    const val NETWORK_TIMEOUT = 603333L
    const val ERROR_MESSAGE = "Cannot proceed your request, please try again later"
    const val OFFLINE_MESSAGE = "No connection, turn your connection active to process"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "c9856d0cb57c3f14bf75bdc6c063b8f3"
    const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original/"
}

object ApiConstant {
    const val DISCOVER_MOVIE = "discover/movie"
    const val MOVIE_DETAIL = "movie/{movie_id}"
    const val API_KEY_QUERY = "api_key"
}