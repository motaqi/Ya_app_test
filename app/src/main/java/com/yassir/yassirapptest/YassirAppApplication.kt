package com.yassir.yassirapptest

import android.app.Application
import android.content.Context
import com.yassir.yassirapptest.di.networkModule
import com.yassir.yassirapptest.di.repositoryModule
import com.yassir.yassirapptest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class YassirAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@YassirAppApplication)
            modules(
                listOf(
                    networkModule, repositoryModule, viewModelModule
                )
            )
        }
    }

    companion object {
        operator fun get(context: Context): Application {
            return context.applicationContext as Application
        }
    }
}