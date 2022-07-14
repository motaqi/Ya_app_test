package com.yassir.yassirapptest.data.network

import okhttp3.Cache
import okhttp3.Interceptor

interface ICacheProvider {
    fun getInterceptor(): Interceptor
    val cache: Cache
}