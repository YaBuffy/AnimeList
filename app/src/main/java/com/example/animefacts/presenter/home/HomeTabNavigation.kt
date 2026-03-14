package com.example.animefacts.presenter.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun HomeTabNavigation(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = HomeTab.Ongoing.route
    ) {
        composable(HomeTab.Upcoming.route) {
            UpcomingAnimeScreen()
        }
        composable(HomeTab.Ongoing.route){
            OngoingAnimeScreen()
        }
        composable(HomeTab.Completed.route){
            CompletedAnimeScreen()
        }
        composable(HomeTab.Movie.route){
            MovieAnimeScreen()
        }
    }
}