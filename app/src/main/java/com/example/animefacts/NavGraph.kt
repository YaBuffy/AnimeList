package com.example.animefacts

sealed class NavGraph(
    val route: String,
) {
    object Home: NavGraph("home_graph")
    object Discover: NavGraph("discover_graph")
    object Bookmark: NavGraph("bookmarks_graph")
    object Stats: NavGraph("stats_graph")
}