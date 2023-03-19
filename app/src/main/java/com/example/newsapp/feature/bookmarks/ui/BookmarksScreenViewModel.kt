package com.example.newsapp.feature.bookmarks.ui

import androidx.lifecycle.viewModelScope
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.base.Event
import com.example.newsapp.feature.bookmarks.domain.BookmarksInteractor
import kotlinx.coroutines.launch

class BookmarksScreenViewModel(private val interactor: BookmarksInteractor) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadBookmarks)
    }

    override fun initialViewState(): ViewState = ViewState(bookmarksArticle = emptyList())

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when(event) {
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
                return previousState.copy(bookmarksArticle = event.bookmarksArticle)
            }
            else -> return null
        }
    }
}