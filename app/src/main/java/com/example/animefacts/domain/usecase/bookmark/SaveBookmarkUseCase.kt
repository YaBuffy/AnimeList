package com.example.animefacts.domain.usecase.bookmark

import com.example.animefacts.domain.model.Bookmark
import com.example.animefacts.domain.repository.BookmarkRepository
import javax.inject.Inject

class SaveBookmarkUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    suspend operator fun invoke(bookmark: Bookmark) = repository.saveBookmark(bookmark)
}