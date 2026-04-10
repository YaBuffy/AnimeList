package com.example.animefacts.domain.model

data class AnimeStatistics(
    val watching: Int,
    val completed: Int,
    val onHold: Int,
    val dropped: Int,
    val planToWatch: Int,
    val scores: List<Score>
)

data class Score(
    val score: Int,
    val votes: Int,
    val percentage: Float
)
