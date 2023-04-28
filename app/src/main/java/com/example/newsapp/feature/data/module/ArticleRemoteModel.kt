package com.example.newsapp.feature.data.module

import com.google.gson.annotations.SerializedName

data class ArticleRemoteModel(
    @SerializedName("author")
    val creator: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val link: String,
    @SerializedName("urlToImage")
    val image_url: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("publishedAt")
    val pubDate: String
)