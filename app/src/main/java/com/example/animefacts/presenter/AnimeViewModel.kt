package com.example.animefacts.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.model.AnimeInfo
import com.example.animefacts.domain.model.AnimeType
import com.example.animefacts.domain.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val repository: AnimeRepository
): ViewModel() {

    private val _animeList = MutableStateFlow<ApiResult<List<Anime>>>(ApiResult.Success(emptyList()))
    val animeList: StateFlow<ApiResult<List<Anime>>> = _animeList

    private val _animeInfo = MutableStateFlow<ApiResult<AnimeInfo>>(ApiResult.Success(AnimeInfo(
        id = 0,
        title = "",
        score = 0.0,
        imageUrl = "",
        type = AnimeType.UNKNOWN,
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
    )))
    val animeInfo: StateFlow<ApiResult<AnimeInfo>> = _animeInfo

    fun loadAnime(){
        viewModelScope.launch {
            _animeList.value = repository.getTopAnime(1)
        }
    }

    fun loadAnimeInfo(id: Int){
        viewModelScope.launch {
            _animeInfo.value = repository.getAnimeInfo(id)
        }
        Log.d("loadInfo", _animeInfo.value.toString())
    }
}