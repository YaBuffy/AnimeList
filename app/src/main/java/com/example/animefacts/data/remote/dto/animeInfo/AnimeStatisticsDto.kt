package com.example.animefacts.data.remote.dto.animeInfo

data class AnimeStatisticsDto(
    val watching: Int,
    val completed: Int,
    val on_hold: Int,
    val dropped: Int,
    val plan_to_watch: Int,
    val scores: List<ScoresDto>
)

data class ScoresDto(
    val score: Int,
    val votes: Int,
    val percentage: Float
)