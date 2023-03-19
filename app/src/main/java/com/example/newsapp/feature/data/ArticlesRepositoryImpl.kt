package com.example.newsapp.feature.data

import com.example.newsapp.feature.domain.ArticleModel

class ArticlesRepositoryImpl(private val source: ArticlesRemoteSource) : ArticlesRepository {
    override suspend fun getArticles(): List<ArticleModel> {
        return source.getArticles().articleList.map {
            it.toDomain()
        }.mapIndexed { index, articleModel ->
            articleModel.copy(
                publishedAt = index.toString()
            )
        }.filter {
            it.publishedAt.toInt() % 2 == 0
        }.toList()
    }
}