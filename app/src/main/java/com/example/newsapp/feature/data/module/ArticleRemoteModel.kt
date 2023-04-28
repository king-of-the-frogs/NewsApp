package com.example.newsapp.feature.data.module

import com.google.gson.annotations.SerializedName

data class ArticleRemoteModel(
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("publishedAt")
    val publishedAt: String
)