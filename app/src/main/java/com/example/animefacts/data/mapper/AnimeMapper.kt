package com.example.animefacts.data.mapper

import com.example.animefacts.data.remote.dto.AnimeDto
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.model.AnimeType


fun AnimeDto.toDomain(): Anime {
    val mainTitle = titles.first{it.type == "Default"}.title
    return Anime(
        id = mal_id ?: 0,
        title = mainTitle,
        score = score ?: 0.0,
        type = type.toAnimeType(),
        imageUrl = images?.jpg?.image_url ?: ""
    )
}

fun String?.toAnimeType(): AnimeType{
    return when(this){
        "TV" -> AnimeType.TV
        "Movie" -> AnimeType.MOVIE
        "OVA" -> AnimeType.OVA
        "ONA" -> AnimeType.ONA
        "Special" -> AnimeType.SPECIAL
        "Music" -> AnimeType.MUSIC
        else -> AnimeType.UNKNOWN
    }
}