package com.example.test.di

import com.example.test.data.remote.MovieApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.sin

private const val BASE_URL = "https://api.themoviedb.org/"

val networkModule = module {
    single {
        provideLoggingInterceptor()
    }
    single {
        provideOkHttpClient(get())
    }
    single {
        provideRetrofit(BASE_URL, get())
    }
    single {
        provideMovieApiService(get())
    }
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return loggingInterceptor
}

fun provideOkHttpClient(loggingInterceptor : HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}

fun provideRetrofit(url : String, okHttpClient : OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideMovieApiService(retrofit: Retrofit): MovieApi {
    return retrofit.create(MovieApi::class.java)
}