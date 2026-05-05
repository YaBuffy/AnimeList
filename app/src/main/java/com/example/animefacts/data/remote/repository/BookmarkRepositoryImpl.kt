package com.example.animefacts.data.remote.repository

import com.example.animefacts.data.local.dao.BookmarkDao
import com.example.animefacts.data.mapper.toDomain
import com.example.animefacts.data.mapper.toEntity
import com.example.animefacts.domain.model.Bookmark
import com.example.animefacts.domain.model.ViewingStatus
import com.example.animefacts.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

}