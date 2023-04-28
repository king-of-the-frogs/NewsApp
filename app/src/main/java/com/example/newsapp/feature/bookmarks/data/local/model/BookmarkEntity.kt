package com.example.newsapp.feature.bookmarks.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.feature.bookmarks.di.BOOKMARKS_TABLE

@Entity(tableName = BOOKMARKS_TABLE)
data class BookmarkEntity(
    @PrimaryKey
    @ColumnInfo(name = "url")
    val link: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "author")
    val creator: String?,
    @ColumnInfo(name = "publishedAt")
    val pubDate: String,
    @ColumnInfo(name = "urlToImage")
    val image_url: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "content")
    val content: String?,

    )
