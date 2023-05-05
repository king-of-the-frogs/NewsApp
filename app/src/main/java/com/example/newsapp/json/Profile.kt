package com.example.newsapp.json

data class Profile(
    val dob: String,
    val name: String,
    val about: String,
    val address: String,
    val company: String,
    val location: Location
)
