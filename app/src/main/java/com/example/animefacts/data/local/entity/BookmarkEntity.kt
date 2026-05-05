package com.example.animefacts.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.animefacts.domain.model.ViewingStatus

@Entity(tableName = "Bookmark")
data class BookmarkEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val imageUrl: String,
    val status: String = ViewingStatus.NOT_WATCHED.name,
    val isFavorite: Boolean = false,
    val addedTime: Long = System.currentTimeMillis()
)
