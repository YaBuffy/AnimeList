package com.example.animefacts.presenter.bottomBar

import androidx.annotation.DrawableRes
import com.example.animefacts.R
import com.example.animefacts.Screen

sealed class BottomNavItem(
    val route: String,
    val title: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
) {
    object Home : BottomNavItem(
        route = Screen.Home.route,
        title = "Home",
        selectedIcon = R.drawable.ic_baseline_home,
        unselectedIcon = R.drawable.ic_outline_home
    )
    object Discover : BottomNavItem(
        route = Screen.Discover.route,
        title = "Discover",
        selectedIcon = R.drawable.ic_baseline_explore,
        unselectedIcon = R.drawable.ic_outline_explore
    )
    object Bookmark : BottomNavItem(
        route = Screen.Bookmark.route,
        title = "Bookmark",
        selectedIcon = R.drawable.ic_baseline_bookmark,
        unselectedIcon = R.drawable.ic_outline_bookmark
    )
    object Stats : BottomNavItem(
        route = Screen.Stats.route,
        title = "Stats",
        selectedIcon = R.drawable.ic_round_bar_chart,
        unselectedIcon = R.drawable.ic_round_bar_chart
    )
}