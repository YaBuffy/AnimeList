package com.example.animefacts.data.remote.repository

import com.example.animefacts.data.JikanApi
import com.example.animefacts.data.mapper.toDomain
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val api: JikanApi
): AnimeRepository {
    override suspend fun getAnime(page: Int): List<Anime> {
        val response = api.getAnime(page)
        return response.data.map { it.toDomain() }
    }

    override suspend fun getTopAnime(page: Int): List<Anime> {
        val response = api.getTopAnime(page)
        return response.data.map { it.toDomain() }
    }

    override suspend fun searchAnime(
        query: String,
        page: Int
    ): List<Anime> {
        val response = api.searchAnime(query, page)
        return response.data.map { it.toDomain() }
    }

}