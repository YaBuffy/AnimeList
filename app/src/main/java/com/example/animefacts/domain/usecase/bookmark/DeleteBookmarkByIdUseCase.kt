package com.example.animefacts.domain.usecase.bookmark

import com.example.animefacts.domain.repository.BookmarkRepository
import javax.inject.Inject

class DeleteBookmarkByIdUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    suspend operator fun invoke(id: Int) = repository.deleteBookmarkById(id)
}