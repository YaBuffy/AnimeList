package com.example.animefacts.presentation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
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
import com.example.animefacts.presentation.discover.schedule.ScheduleScreen
import com.example.animefacts.presentation.home.HomeScreen
import com.example.animefacts.presentation.main.information.AnimeInfoScreen
import com.example.animefacts.presentation.main.search.SearchScreen
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
                    modifier = Modifier.padding(paddingValues),
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
                DiscoverScreen(
                    onSearch = {
                        navController.navigate(Screen.Search.route)
                    },
                    onAnimeClick = {
                        navController.navigate(Screen.AnimeInfo.createRoute(it))
                    },
                    onScheduleClick = {
                        navController.navigate(Screen.Schedule.route)
                    },
                    modifier = Modifier.padding(paddingValues)
                )
            }
            composable(Screen.Schedule.route){
                ScheduleScreen(
                    modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
                    onBack = {
                        navController.popBackStack()
                    },
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
            route = NavGraph.Bookmark.route,
            startDestination = Screen.Bookmark.route
        ){

            composable(Screen.Bookmark.route){
                BookmarkScreen(
                    modifier = Modifier.padding(paddingValues),
                    onAnimeClick = {
                        navController.navigate(Screen.AnimeInfo.createRoute(it))
                    }
                )
            }

        }

        navigation(
            route = NavGraph.Stats.route,
            startDestination = Screen.Stats.route
        ){

            composable(Screen.Stats.route){
                StatsScreen(
                    modifier = Modifier.padding(paddingValues),
                    onAnimeClick = {
                        navController.navigate(Screen.AnimeInfo.createRoute(it))
                    },
                    onBookmarkClick = {
                        navController.navigate(Screen.Bookmark.route){
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true

                        }


                    }
                )
            }
        }

    }
}