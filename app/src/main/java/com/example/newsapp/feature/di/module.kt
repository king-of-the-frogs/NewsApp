package com.example.newsapp.feature.di

import com.example.newsapp.feature.data.ArticlesRemoteSource
import com.example.newsapp.feature.data.ArticlesRepository
import com.example.newsapp.feature.data.ArticlesRepositoryImpl
import com.example.newsapp.feature.data.NewsApi
import com.example.newsapp.feature.domain.ArticlesInteractor
import com.example.newsapp.feature.mainscreen.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val mainScreenModule = module {
    single<NewsApi> {
        get<Retrofit>().create(NewsApi::class.java)
    }

    single<ArticlesRemoteSource> {
        ArticlesRemoteSource(api = get())
    }

    single<ArticlesRepository> {
        ArticlesRepositoryImpl(source = get())
    }

    single<ArticlesInteractor> {
        ArticlesInteractor(repository = get())
    }

    viewModel {
        MainScreenViewModel(interactor = get(), bookmarksInteractor = get())
    }
}