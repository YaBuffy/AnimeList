package com.example.animefacts.data.remote.dto

data class AnimeInfoDto(
    val mal_id: Int?,
    val images: ImagesDto?,
    val titles: List<TitleDto>?,
    val synopsis: String?,
    val score: Double?,
    val type: String?,
    val trailer: TrailerUrlDto?,
    val episodes: Int?,
    val duration: String?,
    val status: String?,
    val scored_by: Int?,
    val rating: String?,
    val members: Int?,
    val favorites: Int?,
    val genres: List<GenresDto>?,
    val studios: List<StudiosDto>?
)
