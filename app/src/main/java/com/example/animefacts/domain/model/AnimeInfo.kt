package com.example.animefacts.domain.model


data class AnimeInfo(
    val id: Int,
    val title: String,
    val englishTitle: String,
    val score: String,
    val imageUrl: String,
    val type: String,
    val synopsis: String,
    val trailerUrl: String,
    val episodes: Int,
    val duration: String,
    val status: String,
    val scoredBy: String,
    val rating: String,
    val members: Int,
    val favorites: Int,
    val rank: String,
    val season: String,
    val year: String,
    val genres: List<Genre>,
    val studios: List<Studio>
)
