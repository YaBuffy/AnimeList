package com.example.animefacts.domain.usecase.discover

import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.AnimeInfo
import com.example.animefacts.domain.repository.AnimeRepository
import javax.inject.Inject

class GetRandomAnimeInfoUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke(): ApiResult<AnimeInfo> {
        return repository.getRandomAnimeInfo()
    }
}