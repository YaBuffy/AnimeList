package com.example.animefacts.data.remote.dto

data class RecommendationDto(
    val entry: EntryDto
)
data class EntryDto(
    val mal_id: Int,
    val title: String,
    val images: ImagesDto,
)
