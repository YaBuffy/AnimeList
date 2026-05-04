package com.example.animefacts.data.remote.dto.review

import com.example.animefacts.data.remote.dto.common.ImagesDto

data class UserDto(
    val username: String,
    val images: ImagesDto,
)