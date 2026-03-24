package com.example.animefacts.domain.repository

import androidx.paging.PagingData
import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.model.AnimeCategory
import com.example.animefacts.domain.model.AnimeInfo
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
//    suspend fun getAnime(page: Int): ApiResult<List<Anime>>
//    suspend fun getTopAnime(page: Int): ApiResult<List<Anime>>
//    suspend fun searchAnime(
//        query: String,
//        type: String? = null,
//        status: String? = null,
//        rating: String? = null,
//        page: Int
//    ): ApiResult<List<Anime>>
    suspend fun getAnimeInfo(id: Int): ApiResult<AnimeInfo>
//    suspend fun getTopAiring(page: Int): ApiResult<List<Anime>>
//    suspend fun getTopMovie(page: Int): ApiResult<List<Anime>>
//    suspend fun getTopUpcoming(page: Int): ApiResult<List<Anime>>
//    suspend fun getCompleted(page: Int): ApiResult<List<Anime>>
    fun getAnimeByCategory(category: AnimeCategory): Flow<PagingData<Anime>>
    fun searchAnimePaging(
        query: String,
        type: String? = null,
        status: String? = null,
        rating: String? = null,
    ): Flow<PagingData<Anime>>
}