package com.example.newsapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.newsapp.di.APP_DATABASE_FULL_PAGE
import com.example.newsapp.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsapp.feature.bookmarks.data.local.model.BookmarksDao
import com.example.newsapp.feature.fullpage.data.local.model.FullPageDao
import com.example.newsapp.feature.fullpage.data.local.model.FullPageEntity

@Database(entities = [FullPageEntity::class], version = 2)
abstract class AppDataBaseFullPage : RoomDatabase() {
    abstract fun fullPageDao(): FullPageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBaseFullPage? = null

        fun getInstance(context: Context): AppDataBaseFullPage {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBaseFullPage::class.java,
                        APP_DATABASE_FULL_PAGE
                    )
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}