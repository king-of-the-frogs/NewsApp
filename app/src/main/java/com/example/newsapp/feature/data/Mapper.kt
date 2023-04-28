package com.example.newsapp.feature.data

import com.example.newsapp.feature.data.module.ArticleRemoteModel
import com.example.newsapp.feature.domain.ArticleModel

fun ArticleRemoteModel.toDomain() = ArticleModel(
    title = title,
    creator = creator ?: "пусто",
    link = link,
    pubDate = pubDate,
    image_url = image_url ?: "пусто",
    description = description ?: "пусто",
    content = content ?: "пусто",
)

