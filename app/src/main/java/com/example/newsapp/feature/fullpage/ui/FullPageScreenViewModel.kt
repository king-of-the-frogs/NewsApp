package com.example.newsapp.feature.fullpage.ui

import androidx.lifecycle.viewModelScope
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.base.Event
import com.example.newsapp.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsapp.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsapp.feature.domain.ArticleModel
import com.example.newsapp.feature.fullpage.domain.FullPageInteractor
import com.example.newsapp.feature.mainscreen.UiEvent
import kotlinx.coroutines.launch

class FullPageScreenViewModel(
    private val interactor: FullPageInteractor,
    private val bookmarksInteractor: BookmarksInteractor,
) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadFull)
    }

    override fun initialViewState() = ViewState(
        fullList = emptyList(),
        fullShown = emptyList(),
    )

    fun deleteBookmark(article: ArticleModel) {
        processDataEvent(com.example.newsapp.feature.bookmarks.ui.DataEvent.DelBookmark(article))
    }

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        return when (event) {
            is DataEvent.LoadFull -> {
                viewModelScope.launch {
                    interactor.read().fold(
                        onError = {},
                        onSuccess = {
                            processDataEvent(DataEvent.OnSuccessFullLoaded(it))
                        }
                    )
                }
                return null
            }

            is DataEvent.OnSuccessFullLoaded -> {
                return previousState.copy(
                    fullShown = event.full,
                    fullList = event.full
                )
            }

            is UiEvent.OnBookmarkClicked -> {
                viewModelScope.launch {
                    bookmarksInteractor.create(event.article)
                }
                return null
            }

            else -> {
                return null
            }
        }
    }
}