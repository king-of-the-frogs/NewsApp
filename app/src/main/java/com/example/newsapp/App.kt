package com.example.newsapp

import android.app.Application
import com.example.newsapp.di.databaseModule
import com.example.newsapp.di.networkModule
import com.example.newsapp.feature.bookmarks.di.bookmarksModule
import com.example.newsapp.feature.di.mainScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(networkModule, mainScreenModule, bookmarksModule, databaseModule)
        }
    }
}