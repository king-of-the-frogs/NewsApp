package com.example.newsapp.di

import androidx.room.Room
import com.example.newsapp.AppDataBaseFullPage
import com.example.newsapp.AppDatabase
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://newsdata.io/"

const val API_KEY = "pub_213419091a953b0b5bbe8e880ca979f881055"
const val APP_DATABASE = "APP_DATABASE"
const val APP_DATABASE_FULL_PAGE = "APP_DATABASE_FULL_PAGE"

val networkModule = module {

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

val database1Module = module {
    single {
        Room
            .databaseBuilder(androidContext(), AppDatabase::class.java, APP_DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        get<AppDatabase>().bookmarksDao()
    }
}
val database2Module = module {

    single {
        Room
            .databaseBuilder(androidApplication(), AppDataBaseFullPage::class.java, APP_DATABASE_FULL_PAGE)
            .build()
    }
    single {
        get<AppDataBaseFullPage>().fullPageDao()
    }
}