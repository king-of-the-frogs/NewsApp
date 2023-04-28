package com.example.newsapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.newsapp.di.database1Module
import com.example.newsapp.di.database2Module
import com.example.newsapp.di.networkModule
import com.example.newsapp.feature.bookmarks.di.bookmarksModule
import com.example.newsapp.feature.di.mainScreenModule
import com.example.newsapp.feature.fullpage.di.fullPageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                networkModule,
                mainScreenModule,
                bookmarksModule,
                fullPageModule,
                database1Module,
                database2Module
            )
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}