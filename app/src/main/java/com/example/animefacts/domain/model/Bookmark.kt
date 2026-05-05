package com.example.animefacts.domain.model

data class Bookmark(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val status: ViewingStatus,
    val isFavorite: Boolean = false,
    val addedTime: Long
)
