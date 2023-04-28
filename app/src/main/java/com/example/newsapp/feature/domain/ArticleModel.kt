package com.example.newsapp.feature.domain

data class ArticleModel(
    val creator: String?,
    val title: String,
    val link: String,
    val image_url: String?,
    val description: String?,
    val content: String?,
    val pubDate: String
)
