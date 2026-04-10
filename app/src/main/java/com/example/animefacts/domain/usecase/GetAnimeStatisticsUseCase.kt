package com.example.animefacts.domain.usecase

import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.AnimeStatistics
import com.example.animefacts.domain.repository.AnimeRepository

class GetAnimeStatisticsUseCase(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke(id: Int): ApiResult<AnimeStatistics> {
        return repository.getAnimeStatistics(id)
    }
}