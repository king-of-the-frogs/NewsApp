package com.example.newsapp.feature.domain

data class ArticleModel(
    val author: String?,
    val title: String,
    val url: String,
    val urlToImage: String?,
    val description: String?,
    val content: String?,
    val publishedAt: String
)
