package com.example.animefacts.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.animefacts.data.mapper.toDomain
import com.example.animefacts.domain.model.Anime

class   SearchPagingSource(
    private val api: JikanApi,
    private val query: String,
    private val type: String?,
    private val status: String?,
    private val rating: String?
): PagingSource<Int, Anime>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val page = params.key ?: 1
        return try {
            val response = api.searchAnime(
                query = query,
                type = type,
                status = status,
                rating = rating,
                page = page
            )
            val data = response.data.map{it.toDomain()}
            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = if(data.isEmpty()) null else page + 1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? = null

}