package com.example.animefacts.data.remote.repository

import android.util.Log
import com.example.animefacts.data.JikanApi
import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.data.mapper.toDomain
import com.example.animefacts.data.remote.utils.safeApiCall
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.model.AnimeInfo
import com.example.animefacts.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val api: JikanApi
): AnimeRepository {
    override suspend fun getAnime(page: Int): ApiResult<List<Anime>> {
        return safeApiCall {
            api.getAnime(page = page).data.map { it.toDomain() }
        }
    }

    override suspend fun getTopAnime(page: Int): ApiResult<List<Anime>> {
        return safeApiCall {
            api.getTopAnime(page).data.map { it.toDomain() }
        }
    }

    override suspend fun searchAnime(
        query: String,
        type: String?,
        status: String?,
        rating: String?,
        page: Int
    ): ApiResult<List<Anime>> {
        return safeApiCall {
            api.searchAnime(
                query = query,
                type = type,
                status = status,
                rating = rating,
                page = page
            ).data.map { it.toDomain() }
        }
    }

    override suspend fun getAnimeInfo(id: Int): ApiResult<AnimeInfo> {
        return safeApiCall {
            api.getAnimeInfo(id).data.toDomain()
        }
    }

    override suspend fun getTopAiring(page: Int): ApiResult<List<Anime>> {
        return safeApiCall {
            api.getTopAiring(page = page).data.map { it.toDomain() }
        }
    }

    override suspend fun getTopMovie(page: Int): ApiResult<List<Anime>> {
        return safeApiCall {
            api.getTopMovie(page = page).data.map {
                Log.d("anime", it.toString())
                it.toDomain() }
        }
    }

    override suspend fun getTopUpcoming(page: Int): ApiResult<List<Anime>> {
        return safeApiCall {
            api.getTopUpcoming(page = page).data.map { it.toDomain() }
        }
    }

    override suspend fun getCompleted(page: Int): ApiResult<List<Anime>> {
        return safeApiCall {
            api.getCompleted(page = page).data.map{it.toDomain()}
        }
    }

}