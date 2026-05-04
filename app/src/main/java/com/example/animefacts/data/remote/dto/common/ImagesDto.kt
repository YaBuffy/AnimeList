package com.example.animefacts.data.remote.dto.common

data class ImagesDto(
    val jpg: ImagesUrlDto?
)

data class ImagesUrlDto(
    val large_image_url: String?,
    val image_url: String?
)
