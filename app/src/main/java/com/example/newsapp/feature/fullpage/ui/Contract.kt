package com.example.newsapp.feature.fullpage.ui

import com.example.newsapp.base.Event
import com.example.newsapp.feature.domain.ArticleModel
import com.example.newsapp.feature.mainscreen.UiEvent

data class ViewState(
    val fullShown: List<ArticleModel>,
    val fullList: List<ArticleModel>
)

sealed class DataEvent() : Event {
    object LoadFull : DataEvent()
    data class OnSuccessFullLoaded(val full: List<ArticleModel>) : DataEvent()
}