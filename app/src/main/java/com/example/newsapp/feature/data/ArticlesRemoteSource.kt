package com.example.newsapp.feature.data


import com.example.newsapp.feature.data.module.ArticlesRemoteModel

class ArticlesRemoteSource(private val api: NewsApi) {

    suspend fun getArticles(): ArticlesRemoteModel {
        return api.getArticles()
    }

    suspend fun getWorldArticles(): ArticlesRemoteModel {
        return api.getWorldArticles()
    }

    suspend fun postArticles(): ArticlesRemoteModel {
        return api.postArticles()
    }
}