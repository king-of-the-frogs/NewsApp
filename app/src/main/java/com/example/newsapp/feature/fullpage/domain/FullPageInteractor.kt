package com.example.newsapp.feature.fullpage.domain

import com.example.newsapp.base.Either
import com.example.newsapp.base.attempt
import com.example.newsapp.feature.domain.ArticleModel
import com.example.newsapp.feature.fullpage.data.local.FullPageRepository

class FullPageInteractor(private val fullPageRepository: FullPageRepository) {

    suspend fun create(model: ArticleModel) {
        attempt { fullPageRepository.create(model) }
    }

    suspend fun read(): Either<Throwable, List<ArticleModel>> {
        return attempt { fullPageRepository.read() }
    }

    suspend fun update(model: ArticleModel) {
        attempt { fullPageRepository.update(model) }
    }

    suspend fun delete(model: ArticleModel) {
        attempt { fullPageRepository.delete(model) }
    }


}
