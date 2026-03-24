package com.example.animefacts.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.animefacts.data.local.AnimeDatabase
import com.example.animefacts.data.local.entity.AnimeEntity
import com.example.animefacts.data.mapper.toEntity
import com.example.animefacts.domain.model.AnimeCategory

@OptIn(ExperimentalPagingApi::class)
class AnimeRemoteMediator(
    val db: AnimeDatabase,
    val api: JikanApi,
    val category: AnimeCategory
): RemoteMediator<Int, AnimeEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AnimeEntity>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.APPEND -> state.pages.size + 1
            LoadType.PREPEND -> return MediatorResult.Success(true)
        }

        return try {
            val response = when(category) {
                AnimeCategory.ONGOING -> api.getTopAiring(page = page)
                AnimeCategory.MOVIE -> api.getTopMovie(page = page)
                AnimeCategory.UPCOMING -> api.getTopUpcoming(page = page)
                AnimeCategory.COMPLETED -> api.getCompleted(page = page)
            }
            val list = response.data.map { it.toEntity().copy(category = category.value) }

            db.withTransaction {
                if(loadType == LoadType.REFRESH){
                    db.animeDao().clearCategory(category.value)
                }
                db.animeDao().insertAll(list)
                }
            MediatorResult.Success(
                endOfPaginationReached = list.isEmpty()
            )
        }catch (e: Exception){
            MediatorResult.Error(e)
        }

    }
}