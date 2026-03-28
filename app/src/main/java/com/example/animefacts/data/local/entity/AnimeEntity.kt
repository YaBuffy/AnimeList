package com.example.animefacts.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val score: String,
    val imageUrl: String,
    val type: String,
    val year: String,
    val category: String,
    val sortOrder: Int
)