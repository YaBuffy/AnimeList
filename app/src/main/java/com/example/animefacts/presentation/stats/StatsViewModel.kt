package com.example.animefacts.presentation.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animefacts.domain.model.ViewingStats
import com.example.animefacts.domain.model.ViewingStatus
import com.example.animefacts.domain.usecase.bookmark.CalculateStatsUseCase
import com.example.animefacts.domain.usecase.bookmark.GetBookmarksByStatusUseCase
import com.example.animefacts.domain.usecase.bookmark.GetRecentlyWatchedUseCase
import com.example.animefacts.domain.usecase.bookmark.GetWeeklyActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val calculateStatsUseCase: CalculateStatsUseCase,
    private val getBookmarksByStatusUseCase: GetBookmarksByStatusUseCase,
    private val getWeeklyActivityUseCase: GetWeeklyActivityUseCase,
    private val getRecentlyWatchedUseCase: GetRecentlyWatchedUseCase
): ViewModel() {

    val stats: StateFlow<ViewingStats> = getBookmarksByStatusUseCase(ViewingStatus.COMPLETED)
        .map { bookmarks ->
            calculateStatsUseCase(bookmarks)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ViewingStats(0,0,0,0)
        )
    val weeklyActivity = getWeeklyActivityUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val recentlyWatched = getRecentlyWatchedUseCase(10)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList())

    val totalWatching = createCountFlow(ViewingStatus.WATCHING)
    val totalCompleted = createCountFlow(ViewingStatus.COMPLETED)
    val totalOnHold = createCountFlow(ViewingStatus.ON_HOLD)
    val totalPlanToWatch = createCountFlow(ViewingStatus.PLAN_TO_WATCH)
    val totalDropped = createCountFlow(ViewingStatus.DROPPED)

    private fun createCountFlow(status: ViewingStatus): StateFlow<Int> {
        return getBookmarksByStatusUseCase(status)
            .map { it.size }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = 0
            )
    }
}