package com.example.animefacts.presentation.main.information

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animefacts.R
import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.AnimeInfo
import com.example.animefacts.domain.model.AnimeStatistics
import com.example.animefacts.domain.model.Recommendation
import com.example.animefacts.domain.usecase.GetAnimeInfoUseCase
import com.example.animefacts.domain.usecase.GetAnimeRecommendationsUseCase
import com.example.animefacts.domain.usecase.GetAnimeStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAnimeInfoUseCase: GetAnimeInfoUseCase,
    private val getAnimeStatisticsUseCase: GetAnimeStatisticsUseCase,
    private val getAnimeRecommendationsUseCase: GetAnimeRecommendationsUseCase,
    @ApplicationContext private val context: Context
): ViewModel() {
    private val animeId: Int = checkNotNull(savedStateHandle["id"])

    private val _animeInfoUIState = MutableStateFlow<InfoUIState>(InfoUIState.Loading)
    val animeInfoUIState = _animeInfoUIState.asStateFlow()



    init {
        getAnimeInfo()
    }

    fun refreshData(){
        getAnimeInfo()
    }

    private fun getAnimeInfo(){
        viewModelScope.launch {
            _animeInfoUIState.value = InfoUIState.Loading

            val infoRes = getAnimeInfoUseCase(animeId)
            delay(500) //Too many Request fix
            val statsRes = getAnimeStatisticsUseCase(animeId)
            delay(500)
            val recsRes = getAnimeRecommendationsUseCase(animeId)

            if (infoRes is ApiResult.Success &&
                statsRes is ApiResult.Success &&
                recsRes is ApiResult.Success)
            {
                _animeInfoUIState.value = InfoUIState.Success(
                    FullAnimeData(
                        info = infoRes.data,
                        stats = statsRes.data,
                        recommendations = recsRes.data
                    )
                )
            } else {
                val errorResult = when {
                    infoRes !is ApiResult.Success -> infoRes
                    statsRes !is ApiResult.Success -> statsRes
                    else -> recsRes
                }
                _animeInfoUIState.value = InfoUIState.Error(mapErrorToMessage(errorResult))
            }
        }
    }
    private fun mapErrorToMessage(result: ApiResult<*>): String {
        return when (result) {
            is ApiResult.NetworkError -> context.getString(R.string.error_network)
            is ApiResult.ServerError -> context.getString(R.string.error_server, result.code, result.message)
            is ApiResult.UnknownError -> context.getString(R.string.error_unknown, result.message)
            else -> context.getString(R.string.error_something_went_wrong)
        }
    }
}

sealed class InfoUIState{
    object Loading: InfoUIState()
    data class Success(val data: FullAnimeData): InfoUIState()
    data class Error(val message: String): InfoUIState()
}
data class FullAnimeData(
    val info: AnimeInfo,
    val stats: AnimeStatistics,
    val recommendations: List<Recommendation>
)