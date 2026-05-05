package com.example.animefacts.domain.usecase.discover

import com.example.animefacts.domain.model.AnimeInfo
import com.example.animefacts.domain.repository.AnimeRepository
import javax.inject.Inject

class SetCachedAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(animeInfo: AnimeInfo) {
        repository.setCachedAnime(animeInfo)
    }
}