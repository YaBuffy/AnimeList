package com.example.animefacts.domain.repository

import com.example.animefacts.domain.model.BarChartSegment
import com.example.animefacts.domain.model.Bookmark
import com.example.animefacts.domain.model.ViewingStats
import com.example.animefacts.domain.model.ViewingStatus
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    suspend fun saveBookmark(bookmark: Bookmark)
    fun getFavorites(): Flow<List<Bookmark>>
    fun getBookmarksByStatus(status: ViewingStatus): Flow<List<Bookmark>>
    suspend fun getBookmarkById(id: Int): Bookmark?
    suspend fun deleteBookmarkById(id: Int)
    suspend fun calculateStats(bookmarks: List<Bookmark>): ViewingStats
    fun getWeeklyActivity(): Flow<List<BarChartSegment>>
    fun getRecentlyWatched(count: Int): Flow<List<Bookmark>>
}