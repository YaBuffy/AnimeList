package com.example.animefacts.domain.usecase

import androidx.paging.PagingData
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.model.AnimeCategory
import com.example.animefacts.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class GetAnimeByCategoryUseCase(
    private val repository: AnimeRepository
) {
    operator fun invoke(category: AnimeCategory): Flow<PagingData<Anime>> {
        return repository.getAnimeByCategory(category)
    }
}