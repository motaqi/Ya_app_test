package com.yassir.yassirapptest.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yassir.yassirapptest.data.entity.MovieDetailResponse
import com.yassir.yassirapptest.data.repository.IMoviesRepository
import com.yassir.yassirapptest.util.NetworkConstant
import com.yassir.yassirapptest.util.Resource
import kotlinx.coroutines.launch

class MovieDetailFragmentViewModel(
    private val repository: IMoviesRepository
) : ViewModel() {

    private val _movieDetail = MutableLiveData<Resource<MovieDetailResponse>>()
    val movieDetail: LiveData<Resource<MovieDetailResponse>> get() = _movieDetail

    fun getMovieById(movieId: String) {
        viewModelScope.launch {
            _movieDetail.postValue(Resource.loading(null))
            try {
                _movieDetail.postValue(Resource.success(repository.getMovieById(movieId)))
            } catch (e: Exception) {
                _movieDetail.postValue(Resource.error(NetworkConstant.ERROR_MESSAGE, null))
            }
        }
    }
}