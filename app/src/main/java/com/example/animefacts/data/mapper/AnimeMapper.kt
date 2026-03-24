package com.example.animefacts.data.mapper

import com.example.animefacts.data.local.entity.AnimeEntity
import com.example.animefacts.data.remote.dto.AnimeDto
import com.example.animefacts.data.remote.dto.AnimeInfoDto
import com.example.animefacts.data.remote.dto.GenresDto
import com.example.animefacts.data.remote.dto.StudiosDto
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.model.AnimeInfo
import com.example.animefacts.domain.model.Genre
import com.example.animefacts.domain.model.Studio


fun AnimeDto.toDomain(): Anime {
    val mainTitle = titles?.first{it.type == "Default"}?.title ?: ""
    val displayYear = when{
        year != null -> year.toString()
        aired.from != null -> aired.from.take(4)
        else -> "TBA"
    }
    return Anime(
        id = mal_id ?: 0,
        title = mainTitle,
        score = score?.toString()?.take(4) ?: "0.00",
        type = type ?: "",
        imageUrl = images?.jpg?.large_image_url ?: "",
        year = displayYear
    )
}

fun AnimeDto.toEntity(): AnimeEntity {
    val mainTitle = titles?.first{it.type == "Default"}?.title ?: ""
    val displayYear = when{
        year != null -> year.toString()
        aired.from != null -> aired.from.take(4)
        else -> "TBA"
    }
    return AnimeEntity(
        id = mal_id ?: 0,
        title = mainTitle,
        score = score?.toString()?.take(4) ?: "0.00",
        type = type ?: "",
        imageUrl = images?.jpg?.large_image_url ?: "",
        year = displayYear,
        category = ""
    )
}

fun AnimeEntity.toDomain(): Anime{
    return Anime(
        id = id,
        title = title,
        score = score,
        type = type,
        imageUrl = imageUrl,
        year = year
    )
}

fun AnimeInfoDto.toDomain(): AnimeInfo{
    val mainTitle = titles?.first{it.type == "Default"}?.title ?: ""
    return AnimeInfo(
        id = mal_id ?: 0,
        title = mainTitle,
        score = score ?: 0.0,
        type = type ?: "",
        imageUrl = images?.jpg?.large_image_url ?: "",
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