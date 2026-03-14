package com.example.animefacts.data.remote.dto

data class ImagesDto(
    val jpg: ImagesUrlDto?
)

data class ImagesUrlDto(
    val large_image_url: String?
)
