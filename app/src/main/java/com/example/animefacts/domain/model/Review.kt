package com.example.animefacts.domain.model

data class Review(
    val user: User,
    val animeId: Int,
    val animeTitle: String,
    val malId: Int,
    val date: String,
    val review: String,
    val score: String,
    val isSpoiler: Boolean,
    val avatarUrl: String
)

data class User(
    val username: String,
    val images: String,
)

