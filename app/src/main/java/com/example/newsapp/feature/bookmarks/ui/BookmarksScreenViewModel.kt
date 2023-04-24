package com.example.newsapp.feature.bookmarks.ui

import androidx.lifecycle.viewModelScope
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.base.Event
import com.example.newsapp.feature.bookmarks.data.local.BookmarksDao
import com.example.newsapp.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsapp.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsapp.feature.domain.ArticleModel
import kotlinx.coroutines.launch

class BookmarksScreenViewModel(
    private val interactor: BookmarksInteractor,
    private val bookmarksDao: BookmarksDao
) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadBookmarks)
    }

    override fun initialViewState() = ViewState(
        bookmarksList = emptyList(),
        bookmarksShown = emptyList(),
    )

    fun deleteBookmark(article: ArticleModel) {
        processDataEvent(DataEvent.DelBookmark(article))
    }

    fun ArticleModel.toBookmarkEntity(): BookmarkEntity {
        return BookmarkEntity(
            title = this.title,
            url = this.url,
            author = this.author,
            publishedAt = this.publishedAt,
        )
    }

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        return when (event) {
            is DataEvent.LoadBookmarks -> {
                viewModelScope.launch {
                    interactor.read().fold(
                        onError = {},
                        onSuccess = {
                            processDataEvent(DataEvent.OnSuccessBookmarksLoaded(it))
                        }
                    )
                }
                return null
            }

            is DataEvent.OnSuccessBookmarksLoaded -> {
                return previousState.copy(
                    bookmarksShown = event.bookmarks,
                    bookmarksList = event.bookmarks
                )
            }

            is DataEvent.DelBookmark -> {
                val bookmarks = previousState.bookmarksList.toMutableList()
                bookmarks.remove(event.article)
                viewModelScope.launch {
                    bookmarksDao.delete(event.article.toBookmarkEntity()) // удаляем данные о новости из базы данных
                }
                return previousState.copy(
                    bookmarksList = bookmarks,
                    bookmarksShown = bookmarks
                )
            }

            else -> {
                return null
            }
        }
    }
}