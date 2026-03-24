package com.example.animefacts.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animefacts.data.local.entity.AnimeEntity

@Database(
    entities = [AnimeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animeDao(): Dao
}