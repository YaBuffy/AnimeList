package com.example.animefacts.data.remote.dto

data class AnimeResponse(
    val data: List<AnimeDto>,
    val pagination: PaginationDto
)
