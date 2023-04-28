package com.example.newsapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.feature.bookmarks.data.local.model.BookmarksDao
import com.example.newsapp.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsapp.feature.fullpage.data.local.model.FullPageDao
import com.example.newsapp.feature.fullpage.data.local.model.FullPageEntity


@Database(entities = [BookmarkEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookmarksDao(): BookmarksDao

}