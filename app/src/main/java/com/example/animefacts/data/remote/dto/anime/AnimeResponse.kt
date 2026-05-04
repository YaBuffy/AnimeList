package com.example.animefacts.data.remote.dto.anime

import com.example.animefacts.data.remote.dto.common.PaginationDto

data class AnimeResponse(
    val data: List<AnimeDto>,
    val pagination: PaginationDto
)