package com.yassir.yassirapptest.di

import com.yassir.yassirapptest.data.network.Api
import com.yassir.yassirapptest.data.network.CacheProviderImpl
import com.yassir.yassirapptest.data.network.ICacheProvider
import com.yassir.yassirapptest.util.ApiConstant
import com.yassir.yassirapptest.util.NetworkConstant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        MoshiConverterFactory.create()
    }

    single {
        CacheProviderImpl(androidContext().cacheDir) as ICacheProvider
    }

    single {
        val queriesInterceptor = Interceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(ApiConstant.API_KEY_QUERY, NetworkConstant.API_KEY)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            return@Interceptor chain.proceed(request)
        }

        OkHttpClient().newBuilder()
            .connectTimeout(NetworkConstant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NetworkConstant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstant.NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .cache(get<ICacheProvider>().cache)
            .addInterceptor(queriesInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(NetworkConstant.BASE_URL)
            .client(get())
            .addConverterFactory(get<MoshiConverterFactory>())
            .build()
    }

    single {
        get<Retrofit>().create(Api::class.java)
    }
}