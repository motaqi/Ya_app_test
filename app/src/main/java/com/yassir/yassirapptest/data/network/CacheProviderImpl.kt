package com.yassir.yassirapptest.data.network

import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.File
import java.util.concurrent.TimeUnit

class CacheProviderImpl(private val cacheDir: File) : ICacheProvider, Interceptor {

    private val cacheControl by lazy {
        CacheControl.Builder()
            .maxStale(1, TimeUnit.HOURS)
            .maxAge(1, TimeUnit.HOURS)
            .build()
    }

    override fun getInterceptor(): Interceptor {
        return this
    }

    override val cache by lazy {
        Cache(cacheDir, (10 * 1024 * 1024).toLong())
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        return response.newBuilder()
            .removeHeader("Cache-Control")
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}