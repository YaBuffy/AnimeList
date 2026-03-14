package com.example.animefacts

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Discover: Screen("discover")
    object Bookmark: Screen("bookmarks")
    object Stats: Screen("stats")
    object Search: Screen("search")
}