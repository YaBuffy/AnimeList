package com.example.animefacts.data.remote.dto

data class ImagesDto(
    val jpg: ImagesUrlDto?
)

data class ImagesUrlDto(
    val image_url: String?
)
