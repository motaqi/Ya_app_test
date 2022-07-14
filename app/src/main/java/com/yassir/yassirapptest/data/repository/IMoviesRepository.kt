package com.yassir.yassirapptest.data.repository

import com.yassir.yassirapptest.data.entity.MovieDetailResponse
import com.yassir.yassirapptest.data.entity.MovieListResponse
import com.yassir.yassirapptest.util.Resource

interface IMoviesRepository {
    suspend fun getMovies(): MovieListResponse
    suspend fun getMovieById(movieId: String): MovieDetailResponse
}