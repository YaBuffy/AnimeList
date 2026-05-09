package com.example.animefacts.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animefacts.data.local.dao.BookmarkDao
import com.example.animefacts.data.local.dao.Dao
import com.example.animefacts.data.local.dao.RemoteKeysDao
import com.example.animefacts.data.local.entity.AnimeEntity
import com.example.animefacts.data.local.entity.BookmarkEntity
import com.example.animefacts.data.local.entity.RemoteKeys

@Database(
    entities = [AnimeEntity::class, RemoteKeys::class, BookmarkEntity::class],
    version = 5,
    exportSchema = false
)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animeDao(): Dao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun bookmarkDao(): BookmarkDao
}