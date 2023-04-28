package com.example.newsapp.feature.bookmarks.data

import com.example.newsapp.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsapp.feature.domain.ArticleModel

fun BookmarkEntity.toDomain() = ArticleModel(
    title = title,
    link = link,
    creator = creator,
    pubDate = pubDate,
    image_url = image_url,
    description = description,
    content = content,
)

fun ArticleModel.toEntity() = BookmarkEntity(
    title = title,
    link = link,
    creator = creator,
    pubDate = pubDate,
    image_url = image_url,
    description = description,
    content = content,
)
