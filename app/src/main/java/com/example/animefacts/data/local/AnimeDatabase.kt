package com.example.animefacts.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animefacts.data.local.entity.AnimeEntity
import com.example.animefacts.data.local.entity.RemoteKeys

@Database(
    entities = [AnimeEntity::class, RemoteKeys::class],
    version = 2,
    exportSchema = false
)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animeDao(): Dao
    abstract fun remoteKeysDao(): RemoteKeysDao
}