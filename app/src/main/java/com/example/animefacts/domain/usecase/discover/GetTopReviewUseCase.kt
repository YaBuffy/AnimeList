package com.example.animefacts.domain.usecase.discover

import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.Review
import com.example.animefacts.domain.repository.AnimeRepository
import javax.inject.Inject

class GetTopReviewUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke(): ApiResult<List<Review>> {
        return repository.getTopReview()
    }
}