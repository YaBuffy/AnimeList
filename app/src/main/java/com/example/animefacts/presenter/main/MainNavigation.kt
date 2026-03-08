package com.example.animefacts.presenter.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.animefacts.Screen
import com.example.animefacts.presenter.bookmark.BookmarkScreen
import com.example.animefacts.presenter.discover.DiscoverScreen
import com.example.animefacts.presenter.home.HomeScreen
import com.example.animefacts.presenter.stats.StatsScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(paddingValues)
    ){
        composable(Screen.Home.route) {
            HomeScreen()
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
    }
}