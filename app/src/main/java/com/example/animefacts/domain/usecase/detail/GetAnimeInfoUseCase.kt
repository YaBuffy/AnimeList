package com.example.animefacts.domain.usecase.detail

import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.AnimeInfo
import com.example.animefacts.domain.repository.AnimeRepository

class GetAnimeInfoUseCase(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke(id: Int): ApiResult<AnimeInfo> {
        return repository.getAnimeInfo(id)
    }
}