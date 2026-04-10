package com.example.animefacts.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animefacts.data.local.entity.RemoteKeys

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE animeId = :animeId")
    suspend fun remoteKeysAnimeId(animeId: Int): RemoteKeys?

    @Query("DELETE FROM remote_keys WHERE animeId IN (SELECT id FROM anime WHERE category = :category)")
    suspend fun clearRemoteKeysByCategory(category: String)
}