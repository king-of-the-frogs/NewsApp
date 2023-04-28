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


private const val BASE_URL = "https://newsapi.org/"

const val API_KEY = "96089324eb6d4eef80cf2bb453738a51"
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