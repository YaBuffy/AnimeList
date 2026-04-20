package com.example.animefacts.presentation.discover.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.usecase.GetSchedulesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getSchedulesUseCase: GetSchedulesUseCase
): ViewModel() {

    private val scheduleCache = mutableMapOf<String, Flow<PagingData<Anime>>>()

    fun getScheduleForDay(day: String): Flow<PagingData<Anime>> {
        return scheduleCache.getOrPut(day) {
            getSchedulesUseCase(day).cachedIn(viewModelScope)
        }
    }
}
