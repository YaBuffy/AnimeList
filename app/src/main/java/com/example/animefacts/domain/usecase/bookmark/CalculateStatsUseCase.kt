package com.example.animefacts.domain.usecase.bookmark

import com.example.animefacts.domain.model.Bookmark
import com.example.animefacts.domain.model.ViewingStats
import com.example.animefacts.domain.repository.BookmarkRepository
import javax.inject.Inject

class CalculateStatsUseCase @Inject constructor(
    private val repository: BookmarkRepository
){
    suspend operator fun invoke(bookmarks: List<Bookmark>): ViewingStats = repository.calculateStats(bookmarks)
}