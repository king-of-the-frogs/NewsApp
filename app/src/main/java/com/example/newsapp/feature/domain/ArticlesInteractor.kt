package com.example.newsapp.feature.domain

import com.example.newsapp.base.attempt
import com.example.newsapp.feature.data.ArticlesRepository

class ArticlesInteractor(private val repository: ArticlesRepository) {

    suspend fun getArticles() = attempt { repository.getArticles() }
}