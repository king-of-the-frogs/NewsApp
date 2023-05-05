package com.example.newsapp.json

data class User(
    val id: String,
    val email: String,
    val roles: List<String>,
    val apiKey: String,
    val profile: Profile,
    val username: String,
    val createdAt: String,
    val updatedAt: String
)