package com.example.animefacts.data.remote.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.animefacts.data.AnimeRemoteMediator
import com.example.animefacts.data.JikanApi
import com.example.animefacts.data.SearchPagingSource
import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.data.local.AnimeDatabase
import com.example.animefacts.data.mapper.toDomain
import com.example.animefacts.data.remote.utils.safeApiCall
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.model.AnimeCategory
import com.example.animefacts.domain.model.AnimeInfo
import com.example.animefacts.domain.model.AnimeStatistics
import com.example.animefacts.domain.model.Recommendation
import com.example.animefacts.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val db: AnimeDatabase,
    private val api: JikanApi
): AnimeRepository {

    private var cachedAnime: AnimeInfo? = null


    override suspend fun getAnimeInfo(id: Int): ApiResult<AnimeInfo> {
        return safeApiCall {
            api.getAnimeInfo(id).data.toDomain()
        }
    }

    override suspend fun getRandomAnimeInfo(): ApiResult<AnimeInfo> {
        return safeApiCall {
            api.getRandomAnimeInfo().data.toDomain()
        }
    }

    override fun setCachedAnime(anime: AnimeInfo) {
        this.cachedAnime = anime
    }

    override fun getCachedAnime(id: Int): AnimeInfo? {
        return if (cachedAnime?.id == id) cachedAnime else null
    }

    override suspend fun getAnimeStatistics(id: Int): ApiResult<AnimeStatistics> {
        return safeApiCall {
            api.getAnimeStatistics(id).data.toDomain()
        }
    }

    override suspend fun getAnimeRecommendations(id: Int): ApiResult<List<Recommendation>> {
        return safeApiCall {api.getAnimeRecommendations(id).data.map { it.toDomain() }}
    }

    override suspend fun getRecommendations(): ApiResult<List<Recommendation>> {
        return safeApiCall {api.getRecommendations().data.flatMap{ recommendationsDto -> recommendationsDto.entry.map{it.toDomain() }} }
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getAnimeByCategory(category: AnimeCategory): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = 25,
                prefetchDistance = 3,
                pageSize = 25),
            remoteMediator = AnimeRemoteMediator(
                api = api,
                db = db,
                category = category
            ),
            pagingSourceFactory = {
                db.animeDao().pagingSource(category.value)
            }
        ).flow.map { pagingData ->
            pagingData.map{it.toDomain()}
        }
    }

    override fun searchAnimePaging(
        query: String,
        type: String?,
        status: String?,
        rating: String?
    ): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(pageSize = 25),
            pagingSourceFactory = {
                SearchPagingSource(
                    api = api,
                    query = query,
                    type = type,
                    status = status,
                    rating = rating
                )
            }
        ).flow
    }


}