package com.example.animefacts.data.remote.repository

import android.icu.util.Calendar
import com.example.animefacts.data.local.dao.BookmarkDao
import com.example.animefacts.data.mapper.toDomain
import com.example.animefacts.data.mapper.toEntity
import com.example.animefacts.domain.model.BarChartSegment
import com.example.animefacts.domain.model.Bookmark
import com.example.animefacts.domain.model.ViewingStats
import com.example.animefacts.domain.model.ViewingStatus
import com.example.animefacts.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val dao: BookmarkDao
): BookmarkRepository {
    override suspend fun saveBookmark(bookmark: Bookmark) {
        dao.insertOrUpdateBookmark(bookmark.toEntity())
    }

    override fun getFavorites(): Flow<List<Bookmark>> {
        return dao.getFavorites().map {list-> list.map { it.toDomain() } }
    }

    override fun getBookmarksByStatus(status: ViewingStatus): Flow<List<Bookmark>> {
        return dao.getBookmarksByStatus(status).map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getBookmarkById(id: Int): Bookmark? {
        return dao.getBookmarkById(id)?.toDomain()
    }

    override suspend fun deleteBookmarkById(id: Int) {
        dao.deleteBookmarkById(id)
    }

    override suspend fun calculateStats(bookmarks: List<Bookmark>): ViewingStats {
        val totalWatchedMinutes = bookmarks.sumOf { it.watchedEpisodes * it.durationPerEp }
        val totalEpisodes = bookmarks.sumOf { it.watchedEpisodes }

        val days = totalWatchedMinutes / (24 * 60)
        val hours = (totalWatchedMinutes % (24 * 60)) / 60
        val minutes = totalWatchedMinutes % 60

        return ViewingStats(totalEpisodes, days, hours, minutes)
    }

    override fun getWeeklyActivity(): Flow<List<BarChartSegment>> {
        val weekInMills = 7 * 24 * 60 * 60 * 1000
        val startTime = System.currentTimeMillis() - weekInMills

        return dao.getWeeklyActivity(startTime).map { list ->
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dayFormat = SimpleDateFormat("EEE", Locale.getDefault())

            (0..6).map{i->
                val date = Calendar.getInstance().apply {
                    add(Calendar.DAY_OF_YEAR, -i)
                }
                val dateStr = sdf.format(date.time)
                val dateLabel = dayFormat.format(date.time)

                val count = list.find { it.date == dateStr }?.count ?: 0
                BarChartSegment(value = count, label = dateLabel)
            }.reversed()
        }
    }

    override fun getRecentlyWatched(count: Int): Flow<List<Bookmark>> {
        return dao.getRecentlyWatched(count).map { list -> list.map { it.toDomain() } }
    }


}