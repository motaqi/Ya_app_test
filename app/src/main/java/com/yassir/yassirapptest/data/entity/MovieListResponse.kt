package com.yassir.yassirapptest.data.entity

data class MovieListResponse(
    val page: Int,
    val results: List<Movie>
)

data class Movie(
    val id: String,
    val original_title: String,
    val poster_path: String,
    val release_date: String
)