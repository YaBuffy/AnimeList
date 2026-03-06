package com.example.animefacts.data.mapper

import com.example.animefacts.data.remote.dto.AnimeDto
import com.example.animefacts.data.remote.dto.AnimeInfoDto
import com.example.animefacts.data.remote.dto.GenresDto
import com.example.animefacts.data.remote.dto.StudiosDto
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.model.AnimeInfo
import com.example.animefacts.domain.model.AnimeType
import com.example.animefacts.domain.model.Genre
import com.example.animefacts.domain.model.Studio


fun AnimeDto.toDomain(): Anime {
    val mainTitle = titles?.first{it.type == "Default"}?.title ?: ""
    return Anime(
        id = mal_id ?: 0,
        title = mainTitle,
        score = score ?: 0.0,
        type = type.toAnimeType(),
        imageUrl = images?.jpg?.image_url ?: ""
    )
}

fun AnimeInfoDto.toDomain(): AnimeInfo{
    val mainTitle = titles?.first{it.type == "Default"}?.title ?: ""
    return AnimeInfo(
        id = mal_id ?: 0,
        title = mainTitle,
        score = score ?: 0.0,
        type = type.toAnimeType(),
        imageUrl = images?.jpg?.image_url ?: "",
        synopsis = synopsis ?: "",
        trailerUrl = trailer?.url ?: "",
        episodes = episodes ?: 0,
        duration = duration ?: "",
        status = status ?: "",
        scoredBy = scored_by ?: 0,
        rating = rating ?: "",
        members = members ?: 0,
        favorites = favorites ?: 0,
        genres = genres?.map { it.toDomain() } ?: emptyList(),
        studios = studios?.map { it.toDomain() } ?: emptyList()
    )
}

fun GenresDto.toDomain(): Genre{
    return Genre(
        id = mal_id,
        name = name
    )
}

fun StudiosDto.toDomain(): Studio{
    return Studio(
        id = mal_id,
        name = name
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