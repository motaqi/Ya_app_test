package com.yassir.yassirapptest.di

import com.yassir.yassirapptest.data.repository.IMoviesRepository
import com.yassir.yassirapptest.data.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module{
    single { MoviesRepository(get()) as IMoviesRepository }
}