package com.example.animefacts.domain.repository

import androidx.paging.PagingData
import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.model.AnimeCategory
import com.example.animefacts.domain.model.AnimeInfo
import com.example.animefacts.domain.model.AnimeStatistics
import com.example.animefacts.domain.model.Recommendation
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun getAnimeInfo(id: Int): ApiResult<AnimeInfo>
    suspend fun getRandomAnimeInfo(): ApiResult<AnimeInfo>
    fun setCachedAnime(anime: AnimeInfo)
    fun getCachedAnime(id: Int): AnimeInfo?
    suspend fun getAnimeRecommendations(id: Int): ApiResult<List<Recommendation>>
    suspend fun getRecommendations(): ApiResult<List<Recommendation>>
    fun getSchedule(filter: String): Flow<PagingData<Anime>>
    fun getAnimeByCategory(category: AnimeCategory): Flow<PagingData<Anime>>
    fun searchAnimePaging(
        query: String,
        type: String? = null,
        status: String? = null,
        rating: String? = null,
    ): Flow<PagingData<Anime>>

    suspend fun getAnimeStatistics(id: Int): ApiResult<AnimeStatistics>
}
