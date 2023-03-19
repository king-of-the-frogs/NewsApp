package com.example.newsapp.feature.mainscreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.base.Event
import com.example.newsapp.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsapp.feature.domain.ArticlesInteractor
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val interactor: ArticlesInteractor,
    private val bookmarksInteractor: BookmarksInteractor
    ) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadArticles)
    }

    override fun initialViewState() = ViewState(articles = emptyList())

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when(event) {
            is DataEvent.LoadArticles -> {
                viewModelScope.launch {
                   interactor.getArticles().fold(
                       onError = {
                           Log.e("ERROR",it.localizedMessage)
                       },
                       onSuccess = {
                           processDataEvent(DataEvent.OnLoadArticlesSucceed(it))
                       }
                   )
                }
                return null
            }
            is DataEvent.OnLoadArticlesSucceed -> {
                return previousState.copy(articles = event.articles)
            }
            is UiEvent.OnArticleClicked -> {
                viewModelScope.launch{
                    bookmarksInteractor.create(previousState.articles[event.index])
                }
                return null
            }
            else -> return null
        }
    }
}