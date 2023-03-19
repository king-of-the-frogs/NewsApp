package com.example.newsapp.feature.domain

import android.media.Image

data class ArticleModel (
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
)