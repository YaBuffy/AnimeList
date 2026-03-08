package com.example.animefacts.domain.repository

import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.model.AnimeInfo

interface AnimeRepository {
    suspend fun getAnime(page: Int): ApiResult<List<Anime>>
    suspend fun getTopAnime(page: Int): ApiResult<List<Anime>>
    suspend fun searchAnime(query: String, page: Int): ApiResult<List<Anime>>
    suspend fun getAnimeInfo(id: Int): ApiResult<AnimeInfo>
    suspend fun getTopAiring(page: Int): ApiResult<List<Anime>>
    suspend fun getTopMovie(page: Int): ApiResult<List<Anime>>
    suspend fun getTopUpcoming(page: Int): ApiResult<List<Anime>>
}