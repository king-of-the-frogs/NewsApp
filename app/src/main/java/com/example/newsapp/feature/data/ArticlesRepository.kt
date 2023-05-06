package com.example.newsapp.feature.data

import com.example.newsapp.feature.domain.ArticleModel

interface ArticlesRepository {

    suspend fun getArticles(): List<ArticleModel>

    suspend fun getWorldArticles(): List<ArticleModel>
}