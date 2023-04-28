package com.example.newsapp.feature.fullpage.di

import com.example.newsapp.feature.fullpage.data.local.FullPageLocalSource
import com.example.newsapp.feature.fullpage.data.local.FullPageRepository
import com.example.newsapp.feature.fullpage.data.local.FullPageRepositoryImpl
import com.example.newsapp.feature.fullpage.domain.FullPageInteractor
import com.example.newsapp.feature.fullpage.ui.FullPageScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val FULL_PAGE_TABLE = "FULL_PAGE_TABLE"

val fullPageModule = module {

    single {
        FullPageLocalSource(fullPageDao = get())
    }

    single<FullPageRepository> {
        FullPageRepositoryImpl(fullPageLocalSource = get())
    }
    single {
        FullPageInteractor(fullPageRepository = get())
    }

    viewModel {
        FullPageScreenViewModel(interactor = get(), bookmarksInteractor = get())
    }
}