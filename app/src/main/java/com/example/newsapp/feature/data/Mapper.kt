package com.example.newsapp.feature.data

import com.example.newsapp.feature.data.module.ArticleRemoteModel
import com.example.newsapp.feature.domain.ArticleModel

fun ArticleRemoteModel.toDomain() = ArticleModel(
    title = title,
    author = author ?: "",
    url = url,
    publishedAt = publishedAt,
)