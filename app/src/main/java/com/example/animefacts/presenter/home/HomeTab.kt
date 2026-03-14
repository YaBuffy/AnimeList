package com.example.animefacts.presenter.home

sealed class HomeTab(
    val route: String,
    val title: String
) {
    object Upcoming: HomeTab("upcoming", "Upcoming")
    object Ongoing: HomeTab("ongoing", "Ongoing")
    object Completed: HomeTab("completed", "Completed")
    object Movie: HomeTab("movie", "Movie")
}