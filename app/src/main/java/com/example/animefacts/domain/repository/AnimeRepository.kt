package com.example.animefacts.domain.repository

import com.example.animefacts.domain.model.Anime
import retrofit2.http.Query

interface AnimeRepository {
    suspend fun getAnime(page: Int): List<Anime>
    suspend fun getTopAnime(page: Int): List<Anime>
    suspend fun searchAnime(query: String, page: Int): List<Anime>
}