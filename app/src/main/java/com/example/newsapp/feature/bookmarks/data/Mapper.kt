package com.example.newsapp.feature.bookmarks.data

import com.example.newsapp.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsapp.feature.domain.ArticleModel

fun BookmarkEntity.toDomain() = ArticleModel(
    title = title,
    url = url,
    author = author,
    publishedAt = publishedAt,
    urlToImage = urlToImage,
    description = description,
    content = content,
)

fun ArticleModel.toEntity() = BookmarkEntity(
    title = title,
    url = url,
    author = author,
    publishedAt = publishedAt,
    urlToImage = urlToImage,
    description = description,
    content = content,
)
