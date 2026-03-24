package com.example.animefacts.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animefacts.data.local.entity.AnimeEntity

@Dao
interface Dao {

    @Query("SELECT * FROM anime WHERE category = :category")
    fun pagingSource(category: String): PagingSource<Int, AnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<AnimeEntity>)

    @Query("DELETE FROM anime WHERE category = :category")
    suspend fun clearCategory(category: String)

}