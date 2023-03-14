package com.example.newsapp

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://newsapi.org/"

const val API_KEY = "96089324eb6d4eef80cf2bb453738a51"

val appModule = module {

    single<OkHttpClient> {
        OkHttpClient
            .Builder()
            .build()
    }

    single<Retrofit> {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }
}