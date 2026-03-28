package com.example.animefacts.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.animefacts.data.local.AnimeDatabase
import com.example.animefacts.data.local.entity.AnimeEntity
import com.example.animefacts.data.local.entity.RemoteKeys
import com.example.animefacts.data.mapper.toEntity
import com.example.animefacts.domain.model.AnimeCategory

@OptIn(ExperimentalPagingApi::class)
class AnimeRemoteMediator(
    private val db: AnimeDatabase,
    private val api: JikanApi,
    private val category: AnimeCategory
): RemoteMediator<Int, AnimeEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AnimeEntity>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        return try {
            val response = when(category) {
                AnimeCategory.ONGOING -> api.getTopAiring(page = page)
                AnimeCategory.MOVIE -> api.getTopMovie(page = page)
                AnimeCategory.UPCOMING -> api.getTopUpcoming(page = page)
                AnimeCategory.COMPLETED -> api.getCompleted(page = page)
            }

            val endOfPaginationReached = !response.pagination.has_next_page

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.remoteKeysDao().clearRemoteKeysByCategory(category.value)
                    db.animeDao().clearCategory(category.value)
                }

                val lastItem = db.animeDao().getLastItemByCategory(category.value)
                var currentSortOrder = lastItem?.sortOrder ?: 0

                val animeList = response.data.map {
                    currentSortOrder++
                    it.toEntity(category = category.value, sortOrder = currentSortOrder)
                }

                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                
                val keys = animeList.map {
                    RemoteKeys(animeId = it.id, prevKey = prevKey, nextKey = nextKey)
                }

                db.remoteKeysDao().insertAll(keys)
                db.animeDao().insertAll(animeList)
            }

            Log.d("ongoingList", "Successfully loaded page $page. End reached: $endOfPaginationReached")
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            Log.e("ongoingList", "Error loading page $page", e)
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, AnimeEntity>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { anime -> db.remoteKeysDao().remoteKeysAnimeId(anime.id) }
    }
}