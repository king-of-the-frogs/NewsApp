package com.example.newsapp.feature.bookmarks.ui

import com.example.newsapp.base.Event
import com.example.newsapp.feature.domain.ArticleModel

data class ViewState(
    val bookmarksArticle: List<ArticleModel>
)

sealed class UiEvent()
sealed class DataEvent() : Event {

    object LoadBookmarks : DataEvent()
    data class OnSuccessBookmarksLoaded(val bookmarksArticle: List<ArticleModel>) : DataEvent()
    data class OnFailedBookmarksLoaded(val throwable: Throwable) : DataEvent()
}