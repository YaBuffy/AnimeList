package com.example.animefacts

sealed class Screen(val route: String) {

    //home_graph
    object Home: Screen("home")
    object Search: Screen("search")
    object AnimeInfo: Screen("animeInfo/{id}"){
        fun createRoute(id: Int) = "animeInfo/$id"
    }

    //discover_graph
    object Discover: Screen("discover")
    object Bookmark: Screen("bookmarks")
    object Stats: Screen("stats")
}