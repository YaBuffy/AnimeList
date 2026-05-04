package com.example.animefacts.data.remote.dto.anime

    import com.example.animefacts.data.remote.dto.common.ImagesDto
import com.example.animefacts.data.remote.dto.common.TitleDto

data class AnimeDto(
    val mal_id: Int?,
    val images: ImagesDto?,
    val titles: List<TitleDto>?,
    val title_english: String?,
    val score: Float?,
    val type: String?,
    val year: Int?,
    val aired: AiredDto
)