package com.example.animefacts.data.remote.dto.review

import com.example.animefacts.data.remote.dto.animeInfo.EntryDto

data class ReviewDto(
    val user: UserDto,
    val entry: EntryDto,
    val mal_id: Int,
    val date: String,
    val review: String,
    val score: Int,
    val is_spoiler: Boolean,
)
