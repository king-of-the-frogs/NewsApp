package com.example.newsapp.feature.fullpage.data.local


import com.example.newsapp.feature.domain.ArticleModel
import com.example.newsapp.feature.fullpage.data.toDomain
import com.example.newsapp.feature.fullpage.data.toEntity

class FullPageRepositoryImpl(private val fullPageLocalSource: FullPageLocalSource) :
    FullPageRepository {
    override suspend fun create(model: ArticleModel) {
        fullPageLocalSource.create(model.toEntity())
    }

    override suspend fun read(): List<ArticleModel> {
        return fullPageLocalSource.read().map { it.toDomain() }
    }

    override suspend fun update(model: ArticleModel) {
        fullPageLocalSource.update(model.toEntity())
    }

    override suspend fun delete(model: ArticleModel) {
        fullPageLocalSource.delete(model.toEntity())
    }

}