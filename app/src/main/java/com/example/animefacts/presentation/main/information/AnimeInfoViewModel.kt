package com.example.animefacts.presentation.main.information

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.data.mapper.toBookmark
import com.example.animefacts.domain.model.AnimeInfo
import com.example.animefacts.domain.model.AnimeStatistics
import com.example.animefacts.domain.model.Recommendation
import com.example.animefacts.domain.model.ViewingStatus
import com.example.animefacts.domain.usecase.bookmark.DeleteBookmarkByIdUseCase
import com.example.animefacts.domain.usecase.bookmark.GetBookmarkByIdUseCase
import com.example.animefacts.domain.usecase.bookmark.SaveBookmarkUseCase
import com.example.animefacts.domain.usecase.detail.GetAnimeInfoUseCase
import com.example.animefacts.domain.usecase.detail.GetAnimeRecommendationsUseCase
import com.example.animefacts.domain.usecase.detail.GetAnimeStatisticsUseCase
import com.example.animefacts.domain.usecase.discover.GetCachedAnimeUseCase
import com.example.animefacts.util.mapErrorToMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
    private val getCachedAnimeUseCase: GetCachedAnimeUseCase,
    private val saveBookmarkUseCase: SaveBookmarkUseCase,
    private val getBookmarkByIdUseCase: GetBookmarkByIdUseCase,
    private val deleteBookmarkByIdUseCase: DeleteBookmarkByIdUseCase,
    @ApplicationContext private val context: Context
): ViewModel() {
    private val animeId: Int = checkNotNull(savedStateHandle["id"])

    private val _animeInfoUIState = MutableStateFlow<InfoUIState>(InfoUIState.Loading)
    val animeInfoUIState = _animeInfoUIState.asStateFlow()



    init {
        checkCachedAnime()
        loadBookmarkState()
    }

    fun refreshData(){
        checkCachedAnime()
    }

    private fun checkCachedAnime(){
        val cachedAnime = getCachedAnimeUseCase(animeId)
        if (cachedAnime != null){
            _animeInfoUIState.value = InfoUIState.Success(
                FullAnimeData(
                    info = cachedAnime,
                    stats = emptyStats(),
                    recommendations = emptyList()
                )
            )
            updateMissingDataSilently()
        } else{
            loadFromApi()
        }
    }

    private fun loadFromApi(){
        viewModelScope.launch {
            _animeInfoUIState.value = InfoUIState.Loading

            val infoRes = getAnimeInfoUseCase(animeId)
            val statsRes = getAnimeStatisticsUseCase(animeId)
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
                _animeInfoUIState.value = InfoUIState.Error(mapErrorToMessage(errorResult, context))
            }
        }
    }

    private fun updateMissingDataSilently(){
        viewModelScope.launch {
            val statsRes = getAnimeStatisticsUseCase(animeId)
            val recsRes = getAnimeRecommendationsUseCase(animeId)
            val currentUIState = _animeInfoUIState.value
            if (currentUIState is InfoUIState.Success){
                _animeInfoUIState.value = InfoUIState.Success(
                    currentUIState.data.copy(
                        stats = if(statsRes is ApiResult.Success) statsRes.data else currentUIState.data.stats,
                        recommendations = if(recsRes is ApiResult.Success) recsRes.data else currentUIState.data.recommendations
                    )
                )
            }
        }
    }

    private fun emptyStats() = AnimeStatistics(0, 0, 0, 0, 0, emptyList())

    //Bookmark
    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()
    private val _status = MutableStateFlow<ViewingStatus>(ViewingStatus.NOT_WATCHED)

    val status = _status.asStateFlow()
    private fun loadBookmarkState(){
        viewModelScope.launch {
            val bookmark = getBookmarkByIdUseCase(animeId)
            bookmark?.let {
                _isFavorite.value = it.isFavorite
                _status.value = it.status
            }
        }
    }

    fun toggleFavorite(){
        viewModelScope.launch {
            _isFavorite.value = !isFavorite.value
            syncWithDatabase()
        }
    }

    fun updateViewingStatus(status: ViewingStatus){
        viewModelScope.launch {
            _status.value = status
            syncWithDatabase()
        }
    }

    private suspend fun syncWithDatabase(){
        val currentUIState = _animeInfoUIState.value
        if (currentUIState is InfoUIState.Success) {
            val animeInfo = currentUIState.data.info

            if(!_isFavorite.value && _status.value == ViewingStatus.NOT_WATCHED){
                deleteBookmarkByIdUseCase(animeId)
            } else{
                val bookmark = animeInfo.toBookmark(
                    status = _status.value,
                    isFavorite = _isFavorite.value
                )
                saveBookmarkUseCase(bookmark)
            }
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
