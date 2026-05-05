package com.example.animefacts.domain.usecase.discover

import com.example.animefacts.domain.model.AnimeInfo
import com.example.animefacts.domain.repository.AnimeRepository
import javax.inject.Inject

class GetCachedAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(id: Int): AnimeInfo?{
        return repository.getCachedAnime(id)
    }
}