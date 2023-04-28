package com.example.newsapp.feature.mainscreen


import com.example.newsapp.base.Event
import com.example.newsapp.feature.domain.ArticleModel

data class ViewState(
    val isSearchEnabled: Boolean,
    val articleShown: List<ArticleModel>,
    val articleList: List<ArticleModel>,
)

sealed class UiEvent : Event {
    object OnSearchButtonClicked : UiEvent()
    data class OnSearchEdit(val text : String) : UiEvent()
    data class OnBookmarkClicked(val article: ArticleModel) : UiEvent()
    data class OnFullClick(val article: ArticleModel) : UiEvent()
}

sealed class DataEvent : Event {
    object LoadArticles : DataEvent()
    data class OnLoadArticlesSucceed(val articles: List<ArticleModel>) : DataEvent()
}