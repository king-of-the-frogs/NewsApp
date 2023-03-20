package com.example.newsapp.feature.mainscreen


import android.widget.TextView
import com.example.newsapp.base.Event
import com.example.newsapp.feature.domain.ArticleModel

data class ViewState(
    val isSearchEnabled: Boolean,
    val articleShown: List<ArticleModel>,
    val articleList: List<ArticleModel>,
)
sealed class UiEvent : Event {
    data class OnArticleClicked(val index: Int) : UiEvent()
    object OnSearchButtonClicked : UiEvent()
    data class OnSearchEdit(val text : String) : UiEvent()
}

sealed class DataEvent : Event {
    object LoadArticles : DataEvent()
    data class OnLoadArticlesSucceed(val articles: List<ArticleModel>) : DataEvent()
}