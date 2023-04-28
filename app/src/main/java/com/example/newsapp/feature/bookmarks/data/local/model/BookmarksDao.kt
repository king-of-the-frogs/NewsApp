package com.example.newsapp.feature.bookmarks.data.local.model

import androidx.room.*
import com.example.newsapp.feature.bookmarks.di.BOOKMARKS_TABLE

@Dao
interface BookmarksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(entity: BookmarkEntity)

    @Query("SELECT * FROM $BOOKMARKS_TABLE")
    suspend fun read(): List<BookmarkEntity>

    @Update
    suspend fun update(entity: BookmarkEntity)

    @Delete
    suspend fun delete(entity: BookmarkEntity)

    @Query("DELETE FROM $BOOKMARKS_TABLE WHERE url=:url")
    suspend fun deleteBookmark(url: String)
}