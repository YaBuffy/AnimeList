package com.example.animefacts.presenter.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.model.AnimeInfo
import com.example.animefacts.domain.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AnimeRepository
): ViewModel() {

    private val _upcomingAnimeList =
        MutableStateFlow<ApiResult<List<Anime>>>(ApiResult.Success(emptyList()))
    val upcomingAnimeList: StateFlow<ApiResult<List<Anime>>> = _upcomingAnimeList

    private val _ongoingAnimeList =
        MutableStateFlow<ApiResult<List<Anime>>>(ApiResult.Success(emptyList()))
    val ongoingAnimeList: StateFlow<ApiResult<List<Anime>>> = _ongoingAnimeList

    private val _completedAnimeList =
        MutableStateFlow<ApiResult<List<Anime>>>(ApiResult.Success(emptyList()))
    val completedAnimeList: StateFlow<ApiResult<List<Anime>>> = _completedAnimeList

    private val _movieList =
        MutableStateFlow<ApiResult<List<Anime>>>(ApiResult.Success(emptyList()))
    val movieList: StateFlow<ApiResult<List<Anime>>> = _movieList

    private val _animeInfo = MutableStateFlow<ApiResult<AnimeInfo>>(
        ApiResult.Success(
            AnimeInfo(
                id = 0,
                title = "",
                score = 0.0,
                imageUrl = "",
                type = "",
                synopsis = "",
                trailerUrl = "",
                episodes = 0,
                duration = "",
                status = "",
                scoredBy = 0,
                rating = "",
                members = 0,
                favorites = 0,
                genres = emptyList(),
                studios = emptyList()
            )
        )
    )
    val animeInfo: StateFlow<ApiResult<AnimeInfo>> = _animeInfo

    var query by mutableStateOf("")
        private set

    fun onQueryChange(newQuery: String){
        query = newQuery
    }

    fun onClear(){
        query = ""
    }

    fun loadOngoingAnime(){
        viewModelScope.launch {
            _ongoingAnimeList.value = repository.getTopAiring(1)
        }
    }

    fun loadUpcomingAnime(){
        viewModelScope.launch {
            _upcomingAnimeList.value = repository.getTopUpcoming(1)
        }
    }

    fun loadCompletedAnime(){
        viewModelScope.launch {
            _completedAnimeList.value = repository.getCompleted(1)
        }
    }

    fun loadMovie(){
        viewModelScope.launch {
            _movieList.value = repository.getTopMovie(1)
        }
    }

    fun loadAnimeInfo(id: Int){
        viewModelScope.launch {
            _animeInfo.value = repository.getAnimeInfo(id)
        }
        Log.d("loadInfo", _animeInfo.value.toString())
    }

    fun searchAnime(){
        viewModelScope.launch {
            _upcomingAnimeList.value = repository.searchAnime(query, 1)
        }
    }
}