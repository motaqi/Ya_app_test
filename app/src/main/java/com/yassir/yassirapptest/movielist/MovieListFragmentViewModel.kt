package com.yassir.yassirapptest.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yassir.yassirapptest.data.entity.Movie
import com.yassir.yassirapptest.data.repository.IMoviesRepository
import com.yassir.yassirapptest.util.NetworkConstant
import com.yassir.yassirapptest.util.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieListFragmentViewModel(
    private val repository: IMoviesRepository
) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<Resource<List<Movie>>>()
    val moviesLiveData: LiveData<Resource<List<Movie>>> get() = _moviesLiveData

    fun getMovies() {
        viewModelScope.launch {
            _moviesLiveData.postValue(Resource.loading(null))
            try {
                val moviesList = repository.getMovies().results
                _moviesLiveData.postValue(Resource.success(moviesList))
            } catch (e: Exception) {
                _moviesLiveData.postValue(Resource.error(NetworkConstant.ERROR_MESSAGE, null))
            }
        }
    }
}