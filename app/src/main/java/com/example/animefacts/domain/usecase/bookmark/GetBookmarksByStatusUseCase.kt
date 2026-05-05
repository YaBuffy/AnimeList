package com.example.animefacts.domain.usecase.bookmark

import com.example.animefacts.domain.model.ViewingStatus
import com.example.animefacts.domain.repository.BookmarkRepository
import javax.inject.Inject

class GetBookmarksByStatusUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    operator fun invoke(status: ViewingStatus) = repository.getBookmarksByStatus(status)
}