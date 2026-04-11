package com.example.animefacts.presentation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.animefacts.NavGraph
import com.example.animefacts.Screen
import com.example.animefacts.presentation.bookmark.BookmarkScreen
import com.example.animefacts.presentation.discover.DiscoverScreen
import com.example.animefacts.presentation.home.HomeScreen
import com.example.animefacts.presentation.main.information.AnimeInfoScreen
import com.example.animefacts.presentation.search.SearchScreen
import com.example.animefacts.presentation.stats.StatsScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
){
    NavHost(
        navController = navController,
        startDestination = NavGraph.Home.route,
    ){
        navigation(
            route = NavGraph.Home.route,
            startDestination = Screen.Home.route
        ){
            composable(Screen.Home.route) {
                HomeScreen(
                    onSearch = {
                        navController.navigate(Screen.Search.route)
                    },
                    paddingValues = paddingValues,
                    onAnimeClick = {
                        navController.navigate(Screen.AnimeInfo.createRoute(it))
                    }
                )
            }

            composable(Screen.Search.route){
                SearchScreen(
                    paddingValues = paddingValues,
                    onBack = {
                        navController.popBackStack()
                    },
                    onAnimeClick = {
                        navController.navigate(Screen.AnimeInfo.createRoute(it))
                    }
                )
            }

            composable(
                route = Screen.AnimeInfo.route,
                arguments = listOf(
                    navArgument("id"){
                        type = NavType.IntType
                    }
                )
            ){
                AnimeInfoScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    paddingValues = paddingValues,
                    onAnimeClick = {
                        navController.navigate(Screen.AnimeInfo.createRoute(it))
                    }
                )
            }
        }

        navigation(
            route = NavGraph.Discover.route,
            startDestination = Screen.Discover.route
        ){

            composable(Screen.Discover.route){
                DiscoverScreen()
            }

        }

        navigation(
            route = NavGraph.Bookmark.route,
            startDestination = Screen.Bookmark.route
        ){

            composable(Screen.Bookmark.route){
                BookmarkScreen()
            }

        }

        navigation(
            route = NavGraph.Stats.route,
            startDestination = Screen.Stats.route
        ){

            composable(Screen.Stats.route){
                StatsScreen()
            }
        }

    }
}