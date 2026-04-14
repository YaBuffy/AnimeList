package com.example.animefacts.data.mapper

import com.example.animefacts.data.local.entity.AnimeEntity
import com.example.animefacts.data.remote.dto.AnimeDto
import com.example.animefacts.data.remote.dto.AnimeInfoDto
import com.example.animefacts.data.remote.dto.AnimeStatisticsDto
import com.example.animefacts.data.remote.dto.EntryDto
import com.example.animefacts.data.remote.dto.GenresDto
import com.example.animefacts.data.remote.dto.RecommendationDto
import com.example.animefacts.data.remote.dto.ScoresDto
import com.example.animefacts.data.remote.dto.StudiosDto
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.model.AnimeInfo
import com.example.animefacts.domain.model.AnimeStatistics
import com.example.animefacts.domain.model.Genre
import com.example.animefacts.domain.model.Recommendation
import com.example.animefacts.domain.model.Score
import com.example.animefacts.domain.model.Studio


fun AnimeDto.toDomain(): Anime {
    val mainTitle = titles?.firstOrNull{it.type == "Default"}?.title ?: ""
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

fun AnimeDto.toEntity(category: String, sortOrder: Int): AnimeEntity {
    val mainTitle = titles?.firstOrNull{it.type == "Default"}?.title ?: ""
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
        category = category,
        sortOrder = sortOrder
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
    val mainTitle = titles?.find { it.type == "Default" }?.title
        ?: titles?.find { it.type == "English" }?.title
        ?: titles?.firstOrNull()?.title
        ?: "Unknown Title"
    val englishTitle = titles?.find { it.type == "English" }?.title
        ?: titles?.find { it.type == "Default" }?.title
        ?: titles?.firstOrNull()?.title
        ?: "Unknown Title"
    val newStatus = when(status){
        "Finished Airing" -> "Finished"
        "Currently Airing" -> "Airing"
        else -> status
    }
    return AnimeInfo(
        id = mal_id ?: 0,
        title = mainTitle,
        englishTitle = englishTitle,
        score = score?.toString() ?: "0.0",
        type = type ?: "N/A",
        imageUrl = images?.jpg?.large_image_url ?: "",
        synopsis = synopsis ?: "No description available.",
        trailerUrl = trailer?.embed_url ?: "",
        episodes = episodes ?: 0,
        duration = duration ?: "Unknown duration",
        status = newStatus ?: "",
        scoredBy = scored_by?.toString() ?: "0",
        rating = rating ?: "",
        members = members ?: 0,
        favorites = favorites ?: 0,
        rank = rank?.toString() ?: "-",
        season = season ?: "Unknown",
        year = year?.toString() ?: "",
        genres = genres?.map { it.toDomain() } ?: emptyList(),
        studios = studios?.map { it.toDomain() } ?: emptyList()
    )
}

fun AnimeStatisticsDto.toDomain(): AnimeStatistics {
    return AnimeStatistics(
        watching = watching,
        completed = completed,
        onHold = on_hold,
        dropped = dropped,
        planToWatch = plan_to_watch,
        scores = scores.map { it.toDomain() }

    )
}

fun RecommendationDto.toDomain(): Recommendation {
    return Recommendation(
        id = entry.mal_id,
        title = entry.title,
        imageUrl = entry.images.jpg?.large_image_url ?: ""
    )
}

fun EntryDto.toDomain(): Recommendation {
    return Recommendation(
        id = mal_id,
        title = title,
        imageUrl = images.jpg?.large_image_url ?: ""
    )
}



fun ScoresDto.toDomain(): Score {
    return Score(
        score = score,
        votes = votes,
        percentage = percentage
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