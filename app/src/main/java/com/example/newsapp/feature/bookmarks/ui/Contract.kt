package com.example.newsapp.feature.bookmarks.ui

import androidx.room.Delete
import com.example.newsapp.base.Event
import com.example.newsapp.feature.domain.ArticleModel

data class ViewState(
    val bookmarksShown: List<ArticleModel>,
    val bookmarksList: List<ArticleModel>
)

sealed class DataEvent() : Event {
    object LoadBookmarks : DataEvent()
    data class OnSuccessBookmarksLoaded(val bookmarks: List<ArticleModel>) : DataEvent()
    data class DelBookmark(val article: ArticleModel) : DataEvent()
}