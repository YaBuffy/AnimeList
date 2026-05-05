package com.example.animefacts.domain.usecase.discover

import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.Recommendation
import com.example.animefacts.domain.repository.AnimeRepository
import javax.inject.Inject

class GetRecommendationsUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke(): ApiResult<List<Recommendation>> {
        return repository.getRecommendations()
    }
}