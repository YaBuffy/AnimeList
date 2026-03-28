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
import com.example.animefacts.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val db: AnimeDatabase,
    private val api: JikanApi
): AnimeRepository {
//    override suspend fun getAnime(page: Int): ApiResult<List<Anime>> {
//        return safeApiCall {
//            api.getAnime(page = page).data.map { it.toDomain() }
//        }
//    }
//
//    override suspend fun getTopAnime(page: Int): ApiResult<List<Anime>> {
//        return safeApiCall {
//            api.getTopAnime(page).data.map { it.toDomain() }
//        }
//    }

//    override suspend fun searchAnime(
//        query: String,
//        type: String?,
//        status: String?,
//        rating: String?,
//        page: Int
//    ): ApiResult<List<Anime>> {
//        return safeApiCall {
//            api.searchAnime(
//                query = query,
//                type = type,
//                status = status,
//                rating = rating,
//                page = page
//            ).data.map { it.toDomain() }
//        }
//    }

    override suspend fun getAnimeInfo(id: Int): ApiResult<AnimeInfo> {
        return safeApiCall {
            api.getAnimeInfo(id).data.toDomain()
        }
    }

//    override suspend fun getTopAiring(page: Int): ApiResult<List<Anime>> {
//        return safeApiCall {
//            api.getTopAiring(page = page).data.map { it.toDomain() }
//        }
//    }
//
//    override suspend fun getTopMovie(page: Int): ApiResult<List<Anime>> {
//        return safeApiCall {
//            api.getTopMovie(page = page).data.map {
//                Log.d("anime", it.toString())
//                it.toDomain() }
//        }
//    }
//
//    override suspend fun getTopUpcoming(page: Int): ApiResult<List<Anime>> {
//        return safeApiCall {
//            api.getTopUpcoming(page = page).data.map { it.toDomain() }
//        }
//    }
//
//    override suspend fun getCompleted(page: Int): ApiResult<List<Anime>> {
//        return safeApiCall {
//            api.getCompleted(page = page).data.map{it.toDomain()}
//        }
//    }

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