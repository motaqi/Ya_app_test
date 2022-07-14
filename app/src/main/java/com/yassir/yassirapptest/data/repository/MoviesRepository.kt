package com.yassir.yassirapptest.data.repository

import com.yassir.yassirapptest.data.entity.MovieDetailResponse
import com.yassir.yassirapptest.data.entity.MovieListResponse
import com.yassir.yassirapptest.data.network.Api

class MoviesRepository(private val api: Api) : IMoviesRepository {

    override suspend fun getMovies(): MovieListResponse {
        return api.getMovies()
    }

    override suspend fun getMovieById(movieId: String): MovieDetailResponse {
        return api.getMovieById(movieId)
    }
}