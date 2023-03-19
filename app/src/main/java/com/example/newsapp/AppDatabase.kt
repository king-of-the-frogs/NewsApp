package com.example.newsapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.feature.bookmarks.data.local.BookmarksDao
import com.example.newsapp.feature.bookmarks.data.local.model.BookmarkEntity


@Database(entities = [BookmarkEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun bookmarksDao(): BookmarksDao
 }