package com.example.animefacts.presenter.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.animefacts.Screen
import com.example.animefacts.presenter.bookmark.BookmarkScreen
import com.example.animefacts.presenter.discover.DiscoverScreen
import com.example.animefacts.presenter.home.HomeScreen
import com.example.animefacts.presenter.search.SearchScreen
import com.example.animefacts.presenter.stats.StatsScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ){
        composable(Screen.Home.route) {
            HomeScreen(
                onSearch = {
                    navController.navigate(Screen.Search.route)
                },
                paddingValues = paddingValues
            )
        }

        composable(Screen.Discover.route){
            DiscoverScreen()
        }

        composable(Screen.Bookmark.route){
            BookmarkScreen()
        }

        composable(Screen.Stats.route){
            StatsScreen()
        }

        composable(Screen.Search.route){
            SearchScreen(
                onBack = {
                    navController.popBackStack()
                },
                paddingValues = paddingValues
            )
        }
    }
}