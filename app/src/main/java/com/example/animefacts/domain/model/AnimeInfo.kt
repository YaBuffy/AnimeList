package com.example.animefacts.domain.model


data class AnimeInfo(
    val id: Int,
    val title: String,
    val score: Double,
    val imageUrl: String,
    val type: String,
    val synopsis: String,
    val trailerUrl: String,
    val episodes: Int,
    val duration: String,
    val status: String,
    val scoredBy: Int,
    val rating: String,
    val members: Int,
    val favorites: Int,
    val genres: List<Genre>,
    val studios: List<Studio>
)
