package com.example.animefacts.data.remote.dto.recommendations

import com.example.animefacts.data.remote.dto.animeInfo.EntryDto

data class RecommendationsDto(
    val entry: List<EntryDto>
)