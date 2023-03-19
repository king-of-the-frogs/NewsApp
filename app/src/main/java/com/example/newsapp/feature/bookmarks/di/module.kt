package com.example.newsapp.feature.bookmarks.di

import com.example.newsapp.feature.bookmarks.data.local.BookmarksLocalSource
import com.example.newsapp.feature.bookmarks.data.local.BookmarksRepository
import com.example.newsapp.feature.bookmarks.data.local.BookmarksRepositoryImpl
import com.example.newsapp.feature.bookmarks.domain.BookmarksInteractor
import com.example.newsapp.feature.bookmarks.ui.BookmarksScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val BOOKMARKS_TABLE = "BOOKMARKS_TABLE"

val bookmarksModule = module {

    single {
        BookmarksLocalSource(bookmarksDao = get())
    }

    single<BookmarksRepository> {
        BookmarksRepositoryImpl(bookmarksLocalSource = get())
    }
    single {
        BookmarksInteractor(bookmarksRepository = get())
    }

    viewModel {
        BookmarksScreenViewModel(interactor = get())
    }
}