package com.example.animefacts.data.remote.dto.animeInfo

import com.example.animefacts.data.remote.dto.common.ImagesDto

data class RecommendationDto(
    val entry: EntryDto
)
data class EntryDto(
    val mal_id: Int,
    val title: String,
    val images: ImagesDto,
)
