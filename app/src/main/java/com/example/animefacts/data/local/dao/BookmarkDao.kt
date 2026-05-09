package com.example.animefacts.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animefacts.data.local.entity.BookmarkEntity
import com.example.animefacts.data.local.entity.DayCountEntity
import com.example.animefacts.domain.model.ViewingStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateBookmark(bookmark: BookmarkEntity)

    @Query("SELECT * From Bookmark where isFavorite=1 ORDER BY addedTime DESC")
    fun getFavorites(): Flow<List<BookmarkEntity>>

    @Query("Select * from Bookmark where status = :status order by addedTime")
    fun getBookmarksByStatus(status: ViewingStatus): Flow<List<BookmarkEntity>>

    @Query("Select * from Bookmark where id = :id")
    suspend fun getBookmarkById(id: Int): BookmarkEntity?

    @Query("Delete from Bookmark where id = :id")
    suspend fun deleteBookmarkById(id: Int)

    @Query("""
        select
        strftime('%Y-%m-%d', datetime(addedTime / 1000, 'unixepoch')) as date,
        count(*) as count
        from bookmark
        where addedTime >= :startTime
        group by date
        order by date ASC
    """
    )
    fun getWeeklyActivity(startTime: Long): Flow<List<DayCountEntity>>

    @Query("Select * from Bookmark order by addedTime desc limit :count")
    fun getRecentlyWatched(count: Int): Flow<List<BookmarkEntity>>
}