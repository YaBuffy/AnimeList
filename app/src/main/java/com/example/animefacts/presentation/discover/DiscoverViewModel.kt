package com.example.animefacts.presentation.discover

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animefacts.R
import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.Recommendation
import com.example.animefacts.domain.usecase.GetRandomAnimeInfoUseCase
import com.example.animefacts.domain.usecase.GetRecommendationsUseCase
import com.example.animefacts.domain.usecase.SetCachedAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val getRandomAnimeInfoUseCase: GetRandomAnimeInfoUseCase,
    private val setCachedAnimeUseCase: SetCachedAnimeUseCase,
    private val getRecommendationsUseCase: GetRecommendationsUseCase,
    @ApplicationContext private val context: Context,
): ViewModel(){

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init{
        getRecommendations()
    }
    fun loadRandomAnimeInfo(onSuccess: (Int) -> Unit){
        viewModelScope.launch {
            val result = getRandomAnimeInfoUseCase()
            if(result is ApiResult.Success){
                setCachedAnimeUseCase(result.data)
                onSuccess(result.data.id)
            }
        }
    }

    fun getRecommendations() {
        viewModelScope.launch {
            when (val recommendations = getRecommendationsUseCase()) {
                is ApiResult.Success -> _uiState.value = UiState.Success(recommendations.data)
                is ApiResult.NetworkError -> _uiState.value = UiState.Error(context.getString(R.string.error_network))
                is ApiResult.ServerError -> _uiState.value = UiState.Error(context.getString(R.string.error_server, recommendations.code, recommendations.message))
                is ApiResult.UnknownError -> _uiState.value = UiState.Error(context.getString(R.string.error_unknown, recommendations.message))

            }
        }
    }

}

sealed class UiState{
    object Loading: UiState()
    data class Success(val data: List<Recommendation>): UiState()
    data class Error(val message: String): UiState()
}