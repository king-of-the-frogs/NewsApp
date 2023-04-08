package com.example.newsapp.feature.bookmarks.ui

import androidx.lifecycle.viewModelScope
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.base.Event
import com.example.newsapp.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsapp.feature.domain.ArticleModel
import kotlinx.coroutines.launch

class BookmarksScreenViewModel(private val interactor: BookmarksInteractor) :
    BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadBookmarks)
    }

    override fun initialViewState() = ViewState(
        bookmarksList = emptyList(),
        bookmarksShown = emptyList(),
    )

    fun addBookmark(article: ArticleModel) {
        processDataEvent(DataEvent.AddBookmark(article))
    }

    fun deleteBookmark(article: ArticleModel) {
        processDataEvent(DataEvent.DelBookmark(article))
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

            is DataEvent.AddBookmark -> {
                // Добавление новой закладки в список закладок
                val bookmarks = previousState.bookmarksList.toMutableList()
                bookmarks.add(event.article)
                return previousState.copy(
                    bookmarksList = bookmarks,
                    bookmarksShown = bookmarks,
                    bookmarkAdded = !previousState.bookmarkAdded
                )
            }

            is DataEvent.DelBookmark -> {
                val bookmarks = previousState.bookmarksList.toMutableList()
                bookmarks.remove(event.article)
                return previousState.copy(
                    bookmarksList = bookmarks,
                    bookmarksShown = bookmarks,
                    bookmarkAdded = !previousState.bookmarkAdded
                )
            }

            else -> {
                return null
            }
        }
    }
}