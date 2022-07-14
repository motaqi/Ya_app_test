package com.yassir.yassirapptest.di

import com.yassir.yassirapptest.moviedetail.MovieDetailFragmentViewModel
import com.yassir.yassirapptest.movielist.MovieListFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieListFragmentViewModel(get()) }
    viewModel { MovieDetailFragmentViewModel(get()) }
}