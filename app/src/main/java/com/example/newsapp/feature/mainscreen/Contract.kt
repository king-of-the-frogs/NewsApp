package com.example.newsapp.feature.mainscreen


import com.example.newsapp.base.Event
import com.example.newsapp.feature.domain.ArticleModel

data class ViewState(
    val articles: List<ArticleModel>
)
sealed class UiEvent : Event {
    data class OnArticleClicked(val index: Int) : UiEvent()
}

sealed class DataEvent : Event {
    object LoadArticles : DataEvent()
    data class OnLoadArticlesSucceed(val articles: List<ArticleModel>) : DataEvent()
}