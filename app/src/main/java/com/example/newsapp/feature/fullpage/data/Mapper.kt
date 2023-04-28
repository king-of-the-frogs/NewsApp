package com.example.newsapp.feature.fullpage.data

import com.example.newsapp.feature.domain.ArticleModel
import com.example.newsapp.feature.fullpage.data.local.model.FullPageEntity

fun FullPageEntity.toDomain() = ArticleModel(
    title = title,
    url = url,
    author = author,
    publishedAt = publishedAt,
    urlToImage = urlToImage,
    description = description,
    content = content,
)

fun ArticleModel.toEntity() = FullPageEntity(
    title = title,
    url = url,
    author = author,
    publishedAt = publishedAt,
    urlToImage = urlToImage,
    description = description,
    content = content,
)