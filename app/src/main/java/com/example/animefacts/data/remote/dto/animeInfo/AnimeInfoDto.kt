package com.example.animefacts.data.remote.dto.animeInfo

import com.example.animefacts.data.remote.dto.common.GenresDto
import com.example.animefacts.data.remote.dto.common.ImagesDto
import com.example.animefacts.data.remote.dto.common.StudiosDto
import com.example.animefacts.data.remote.dto.common.TitleDto

data class AnimeInfoDto(
    val mal_id: Int?,
    val images: ImagesDto?,
    val titles: List<TitleDto>?,
    val synopsis: String?,
    val type: String?,
    val trailer: TrailerUrlDto?,
    val episodes: Int?,
    val duration: String?,
    val status: String?,
    val score: Double?,
    val scored_by: Int?,
    val rating: String?,
    val rank: Int?,
    val members: Int?,
    val season: String?,
    val year: Int?,
    val favorites: Int?,
    val genres: List<GenresDto>?,
    val studios: List<StudiosDto>?
)