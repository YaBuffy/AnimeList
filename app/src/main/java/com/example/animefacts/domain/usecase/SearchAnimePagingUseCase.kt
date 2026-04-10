package com.example.animefacts.domain.usecase

import androidx.paging.PagingData
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class SearchAnimePagingUseCase(
    private val repository: AnimeRepository,
) {
    operator fun invoke(
        query: String,
        type: String? = null,
        status: String? = null,
        rating: String? = null,
    ): Flow<PagingData<Anime>>{
        return repository.searchAnimePaging(
            query = query,
            type = type,
            status = status,
            rating = rating,
        )
    }
}