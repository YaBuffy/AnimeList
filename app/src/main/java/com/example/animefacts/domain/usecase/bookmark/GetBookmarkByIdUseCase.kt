package com.example.animefacts.domain.usecase.bookmark

import com.example.animefacts.domain.repository.BookmarkRepository
import javax.inject.Inject

class GetBookmarkByIdUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    suspend operator fun invoke(id: Int) = repository.getBookmarkById(id)
}