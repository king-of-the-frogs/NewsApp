package com.example.newsapp.feature.mainscreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.newsapp.base.BaseViewModel
import com.example.newsapp.base.Event
import com.example.newsapp.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsapp.feature.domain.ArticlesInteractor
import com.example.newsapp.feature.fullnews.NewsAdapter
import kotlinx.coroutines.launch
import java.lang.ref.Cleaner.create
import java.net.URI.create

class MainScreenViewModel(
    private val interactor: ArticlesInteractor,
    private val bookmarksInteractor: BookmarksInteractor
) : BaseViewModel<ViewState>() {

    init {
        processDataEvent(DataEvent.LoadArticles)
    }

    override fun initialViewState() = ViewState(
        articleList = emptyList(),
        articleShown = emptyList(),
        isSearchEnabled = false,
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {

            is DataEvent.LoadArticles -> {
                viewModelScope.launch {
                    interactor.getArticles().fold(
                        onError = {
                            Log.e("ERROR", it.localizedMessage)
                        },
                        onSuccess = {
                            processDataEvent(DataEvent.OnLoadArticlesSucceed(it))
                        }
                    )
                }
                return null
            }
            is DataEvent.OnLoadArticlesSucceed -> {
                return previousState.copy(
                    articleList = event.articles,
                    articleShown = event.articles
                )
            }

            is UiEvent.OnArticleClicked -> {
                viewModelScope.launch {
                    bookmarksInteractor.create(previousState.articleShown[event.index])
                }
                return null
            }

            is UiEvent.OnFullClick -> {
                viewModelScope.launch {
                    bookmarksInteractor.create(event.article)
                }
                return null
            }
            is UiEvent.OnBookmarkClicked -> {
                viewModelScope.launch {
                    bookmarksInteractor.create(event.article)
                }
                return null
            }

            is UiEvent.OnSearchButtonClicked -> {
                return previousState.copy(
                    articleShown = if (!previousState.isSearchEnabled) previousState.articleList else previousState.articleShown,
                    isSearchEnabled = !previousState.isSearchEnabled
                )
            }
            is UiEvent.OnSearchEdit -> {
                return previousState.copy(articleShown = previousState.articleList.filter {
                    it.title.contains(
                        event.text
                    )
                })
            }

            else -> return null
        }
    }
}