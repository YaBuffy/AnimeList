package com.example.animefacts.domain.usecase

import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.Recommendation
import com.example.animefacts.domain.repository.AnimeRepository

data class GetAnimeRecommendationsUseCase(
    private val repository: AnimeRepository
){
    suspend operator fun invoke(id: Int): ApiResult<List<Recommendation>> {
        return repository.getAnimeRecommendations(id)
    }
}
