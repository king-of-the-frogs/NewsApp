package com.example.newsapp.feature.bookmarks.data.local

import com.example.newsapp.feature.bookmarks.data.toDomain
import com.example.newsapp.feature.bookmarks.data.toEntity
import com.example.newsapp.feature.domain.ArticleModel

class BookmarksRepositoryImpl(private val bookmarksLocalSource: BookmarksLocalSource) :
    BookmarksRepository {
    override suspend fun create(model: ArticleModel) {
        bookmarksLocalSource.create(model.toEntity())
    }

    override suspend fun read(): List<ArticleModel> {
        return bookmarksLocalSource.read().map { it.toDomain() }
    }

    override suspend fun update(model: ArticleModel) {
        bookmarksLocalSource.update(model.toEntity())
    }

    override suspend fun delete(model: ArticleModel) {
        bookmarksLocalSource.delete(model.toEntity())
    }

    override suspend fun addBookmark(article: ArticleModel) {
        val bookmarks = bookmarksLocalSource.read().map { it.toDomain() }
        if (bookmarks.any { it.title == article.title }) {
            // Если статья уже находится в закладках, ничего не делаем
            return
        }
        bookmarksLocalSource.create(article.toEntity())
    }
}