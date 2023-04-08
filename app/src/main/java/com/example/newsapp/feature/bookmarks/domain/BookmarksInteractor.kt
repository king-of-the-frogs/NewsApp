package com.example.newsapp.feature.bookmarks.domain

import com.example.newsapp.base.Either
import com.example.newsapp.base.attempt
import com.example.newsapp.feature.bookmarks.data.local.BookmarksRepository
import com.example.newsapp.feature.domain.ArticleModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookmarksInteractor(private val bookmarksRepository: BookmarksRepository) {

    suspend fun create(model: ArticleModel) {
        attempt { bookmarksRepository.create(model) }
    }

    suspend fun read(): Either<Throwable, List<ArticleModel>> {
        return attempt { bookmarksRepository.read() }
    }

    suspend fun update(model: ArticleModel) {
        attempt { bookmarksRepository.update(model) }
    }

    suspend fun delete(model: ArticleModel) {
        attempt { bookmarksRepository.delete(model) }
    }
}
