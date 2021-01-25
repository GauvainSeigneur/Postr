package com.seigneur.gauvain.repository.di

import android.util.Log
import com.seigneur.gauvain.repository.service.ClientIdInterceptor
import com.seigneur.gauvain.repository.service.HeaderAccessTokenInterceptor
import com.seigneur.gauvain.repository.service.UnsplashService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {

    factory<Interceptor> {
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("UNSPLASH API", it)
        }
        )
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get())
            .addNetworkInterceptor(ClientIdInterceptor())
            .addNetworkInterceptor(HeaderAccessTokenInterceptor())
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(getProperty("server_url") as String)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory { get<Retrofit>().create(UnsplashService::class.java) }
}


