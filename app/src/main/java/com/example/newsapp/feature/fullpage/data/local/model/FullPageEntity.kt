package com.example.newsapp.feature.fullpage.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.feature.fullpage.di.FULL_PAGE_TABLE

@Entity(tableName = FULL_PAGE_TABLE)
data class FullPageEntity(
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