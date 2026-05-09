package com.example.animefacts.domain.usecase.bookmark

import com.example.animefacts.domain.repository.BookmarkRepository
import javax.inject.Inject

class GetWeeklyActivityUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    operator fun invoke() = repository.getWeeklyActivity()
}