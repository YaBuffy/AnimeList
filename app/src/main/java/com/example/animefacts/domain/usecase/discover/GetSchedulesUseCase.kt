package com.example.animefacts.domain.usecase.discover

import androidx.paging.PagingData
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSchedulesUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(day: String): Flow<PagingData<Anime>> {
        return repository.getSchedule(filter = day)
    }
}