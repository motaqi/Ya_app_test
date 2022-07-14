package com.yassir.yassirapptest.data.network

import com.yassir.yassirapptest.data.entity.MovieDetailResponse
import com.yassir.yassirapptest.data.entity.MovieListResponse
import com.yassir.yassirapptest.util.ApiConstant
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET(ApiConstant.DISCOVER_MOVIE)
    suspend fun getMovies(): MovieListResponse

    @GET(ApiConstant.MOVIE_DETAIL)
    suspend fun getMovieById(@Path("movie_id") movieId: String): MovieDetailResponse
}