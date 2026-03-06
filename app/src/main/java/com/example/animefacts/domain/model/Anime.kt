package com.example.animefacts.domain.model


data class Anime(
    val id: Int,
    val title: String,
    val score: Double,
    val imageUrl: String,
    val type: AnimeType
)
