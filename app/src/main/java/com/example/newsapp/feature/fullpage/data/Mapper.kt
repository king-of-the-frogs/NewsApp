package com.example.newsapp.feature.fullpage.data

import com.example.newsapp.feature.domain.ArticleModel
import com.example.newsapp.feature.fullpage.data.local.model.FullPageEntity

fun FullPageEntity.toDomain() = ArticleModel(
    title = title,
    link = link,
    creator = creator,
    pubDate = pubDate,
    image_url = image_url,
    description = description,
    content = content,
)

fun ArticleModel.toEntity() = FullPageEntity(
    title = title,
    link = link,
    creator = creator,
    pubDate = pubDate,
    image_url = image_url,
    description = description,
    content = content,
)