package com.example.animefacts.presentation.discover

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.Recommendation
import com.example.animefacts.domain.model.Review
import com.example.animefacts.domain.usecase.discover.GetRandomAnimeInfoUseCase
import com.example.animefacts.domain.usecase.discover.GetRecommendationsUseCase
import com.example.animefacts.domain.usecase.discover.GetTopReviewUseCase
import com.example.animefacts.domain.usecase.discover.SetCachedAnimeUseCase
import com.example.animefacts.util.mapErrorToMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val getRandomAnimeInfoUseCase: GetRandomAnimeInfoUseCase,
    private val setCachedAnimeUseCase: SetCachedAnimeUseCase,
    private val getRecommendationsUseCase: GetRecommendationsUseCase,
    private val getTopReviewUseCase: GetTopReviewUseCase,
    @ApplicationContext private val context: Context,
): ViewModel(){

    private val _uiState = MutableStateFlow<DiscoverUiState>(DiscoverUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init {
        loadAllContent()
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

    fun loadAllContent(){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val recommendations = async { getRecommendationsUseCase()}.await()
            val reviews = async{getTopReviewUseCase()}.await()

            if(recommendations is ApiResult.Success && reviews is ApiResult.Success){
                _uiState.update {
                    it.copy(
                        recommendations = recommendations.data,
                        topReviews = reviews.data,
                        isLoading = false
                    )
                }
            } else {
                val error = when {
                    recommendations !is ApiResult.Success -> mapErrorToMessage(recommendations, context)
                    else -> mapErrorToMessage(reviews, context)
                }

                _uiState.update { it.copy(errorMessage = error, isLoading = false) }
            }
        }
    }

}

data class DiscoverUiState(
    val recommendations: List<Recommendation> = emptyList(),
    val topReviews: List<Review> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)