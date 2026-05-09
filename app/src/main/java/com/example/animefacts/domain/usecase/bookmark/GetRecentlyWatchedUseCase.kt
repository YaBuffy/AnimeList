package com.example.animefacts.domain.usecase.bookmark

import com.example.animefacts.domain.repository.BookmarkRepository
import javax.inject.Inject

class GetRecentlyWatchedUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    operator fun invoke(count: Int) = repository.getRecentlyWatched(count)
}