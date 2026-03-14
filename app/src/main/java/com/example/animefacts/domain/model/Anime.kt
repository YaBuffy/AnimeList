package com.example.animefacts.domain.model


data class Anime(
    val id: Int,
    val title: String,
    val score: String,
    val imageUrl: String,
    val type: String,
    val year: String
)
