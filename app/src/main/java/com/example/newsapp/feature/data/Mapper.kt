package com.example.newsapp.feature.data

import com.example.newsapp.R
import com.example.newsapp.feature.data.module.ArticleRemoteModel
import com.example.newsapp.feature.domain.ArticleModel

fun ArticleRemoteModel.toDomain() = ArticleModel(
    title = title,
    author = author ?: "Unknown author",
    url = url,
    publishedAt = publishedAt,
    urlToImage = urlToImage ?: "",
    description = description ?: "",
    content = content ?: "",
)

