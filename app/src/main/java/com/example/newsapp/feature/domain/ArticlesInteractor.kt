package com.example.newsapp.feature.domain

import android.util.Log
import com.example.newsapp.SampleModel
import com.example.newsapp.base.Either
import com.example.newsapp.base.attempt
import com.example.newsapp.feature.data.ArticlesRepository
import com.example.newsapp.json.User
import com.google.gson.Gson
import com.google.gson.JsonObject

class ArticlesInteractor(private val repository: ArticlesRepository) {

    suspend fun getArticles(): Either<Throwable, List<ArticleModel>> = attempt {

        val gson = Gson()

        val request = gson.toJson(JsonObject().apply {
            addProperty("count", 5)
            addProperty("country", "ru")
            addProperty("list", gson.toJson(listOf("1", "2", "3")))
        })

        Log.e("Log => ", request)

        val response = "{\"count\":5,\"country\":\"ru\",\"list\":[\"123\",\"1234567\"]}"

        gson.fromJson(response, SampleModel::class.java)

        val json = """
    {
        "id": "6212577f0095c22f40b1a78a",
        "email": "fields_tyson@manglo.degree",
        "roles": [
            "owner",
            "guest"
        ],
        "apiKey": "f767f1e7-63e2-4f7b-984d-1f4743e7dfd1",
        "profile": {
            "dob": "1989-02-17",
            "name": "Fields Tyson",
            "about": "Quis labore commodo culpa aliquip cillum deserunt culpa non pariatur minim ullamco reprehenderit nulla esse. Ullamco aliquip do commodo cillum.",
            "address": "59 Menahan Street, Loretto, Washington",
            "company": "Manglo",
            "location": {
                "lat": 56.740646,
                "long": -60.715809
            }
        },
        "username": "fields89",
        "createdAt": "2013-12-09T14:53:16.548Z",
        "updatedAt": "2013-12-10T14:53:16.548Z"
    }
""".trimIndent()

        val user = gson.fromJson(json, User::class.java)

        Log.d("User => ", user.toString())

        val json1 = gson.toJson(user)

        Log.d("Json => ", json1.toString())

        repository.getArticles() }
}