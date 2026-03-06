package com.example.animefacts.data.remote.dto

data class AnimeDto(
    val mal_id: Int?,
    val images: ImagesDto?,
    val titles: List<TitleDto>?,
    val score: Double?,
    val type: String?
)
