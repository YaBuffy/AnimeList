package com.example.animefacts.data

import com.example.animefacts.data.remote.dto.AnimeInfoResponse
import com.example.animefacts.data.remote.dto.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JikanApi {
    @GET("anime")
    suspend fun getAnime(
        @Query("page") page: Int,
    ): AnimeResponse

    @GET("anime")
    suspend fun searchAnime(
        @Query("q") query: String,
        @Query("page") page: Int,
    ): AnimeResponse

    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int,
    ): AnimeResponse

    @GET("anime/{id}")
    suspend fun getAnimeInfo(
        @Path("id") id: Int,
    ): AnimeInfoResponse

    //ongoing
    @GET("top/anime")
    suspend fun getTopAiring(
        @Query("filter") filter: String = "airing",
        @Query("page") page: Int
    ): AnimeResponse

    @GET("top/anime")
    suspend fun getTopMovie(
        @Query("type") type: String = "movie",
        @Query("page") page: Int
    ): AnimeResponse

    @GET("top/anime")
    suspend fun getTopUpcoming(
        @Query("filter") filter: String = "upcoming",
        @Query("page") page: Int
    ): AnimeResponse
}