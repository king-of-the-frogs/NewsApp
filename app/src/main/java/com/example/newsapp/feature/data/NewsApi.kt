package com.example.newsapp.feature.data

import com.example.newsapp.di.API_KEY
import com.example.newsapp.feature.data.module.ArticlesRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getArticles(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("country") country: String = "us",
        @Query("pageSize") pageSize: Int = 100
    ): ArticlesRemoteModel
}