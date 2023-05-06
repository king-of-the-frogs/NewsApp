package com.example.newsapp.feature.data

import com.example.newsapp.feature.domain.ArticleModel

class ArticlesRepositoryImpl(private val source: ArticlesRemoteSource) : ArticlesRepository {

    override suspend fun getArticles(): List<ArticleModel> {
        return source.getArticles().articleList.map {
            it.toDomain()
        }.sortedByDescending { it.publishedAt }
    }

    override suspend fun getWorldArticles(): List<ArticleModel> {
        return source.getWorldArticles().articleList.map {
            it.toDomain()
        }.sortedByDescending { it.publishedAt }
    }
}