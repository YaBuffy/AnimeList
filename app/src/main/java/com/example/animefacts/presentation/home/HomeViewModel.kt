package com.example.animefacts.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.model.AnimeCategory
import com.example.animefacts.domain.model.AnimeRating
import com.example.animefacts.domain.model.AnimeStatus
import com.example.animefacts.domain.model.AnimeType
import com.example.animefacts.domain.usecase.home.GetAnimeByCategoryUseCase
import com.example.animefacts.domain.usecase.search.SearchAnimePagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getAnimeByCategoryUseCase: GetAnimeByCategoryUseCase,
    private val searchAnimePagingUseCase: SearchAnimePagingUseCase
): ViewModel() {
    //Anime List//

    val ongoingAnime = getAnimeByCategoryUseCase(AnimeCategory.ONGOING)
        .cachedIn(viewModelScope)

    val upcomingAnime = getAnimeByCategoryUseCase(AnimeCategory.UPCOMING)
        .cachedIn(viewModelScope)
    val completedAnime = getAnimeByCategoryUseCase(AnimeCategory.COMPLETED)
        .cachedIn(viewModelScope)
    val movieAnime = getAnimeByCategoryUseCase(AnimeCategory.MOVIE)
        .cachedIn(viewModelScope)

    //Search Anime//
    var selectedType by mutableStateOf(AnimeType.ALL)
        private set
    var selectedStatus by mutableStateOf(AnimeStatus.ALL)
        private set
    var selectedRating by mutableStateOf(AnimeRating.ALL)
        private set

    fun onTypeSelected(newType: AnimeType){
        selectedType = newType
    }

    fun onStatusSelected(newStatus: AnimeStatus){
        selectedStatus = newStatus
    }

    fun onRatingSelected(newRating: AnimeRating){
        selectedRating = newRating
    }

    fun clearFilters() {
        selectedType = AnimeType.ALL
        selectedStatus = AnimeStatus.ALL
        selectedRating = AnimeRating.ALL
    }


    var query by mutableStateOf("")
        private set

    fun onQueryChange(newQuery: String){
        query = newQuery
    }

    fun onClear(){
        query = ""
    }

    var pagingFlow = MutableStateFlow<PagingData<Anime>>(PagingData.empty())

    fun searchAnime(){
        viewModelScope.launch {
            searchAnimePagingUseCase(
                query = query,
                type = selectedType.apiValue,
                status = selectedStatus.apiValue,
                rating = selectedRating.apiValue
            ).cachedIn(viewModelScope)
                .collect { pagingFlow.value = it }
        }
    }
}