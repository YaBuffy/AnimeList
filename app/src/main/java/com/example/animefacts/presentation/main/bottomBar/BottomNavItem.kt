package com.example.animefacts.presentation.main.bottomBar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.animefacts.R
import com.example.animefacts.Screen

sealed class BottomNavItem(
    val route: String,
    @StringRes val titleRes: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
) {
    object Home : BottomNavItem(
        route = Screen.Home.route,
        titleRes =  R.string.home,
        selectedIcon = R.drawable.ic_baseline_home,
        unselectedIcon = R.drawable.ic_outline_home
    )
    object Discover : BottomNavItem(
        route = Screen.Discover.route,
        titleRes = R.string.discover,
        selectedIcon = R.drawable.ic_baseline_explore,
        unselectedIcon = R.drawable.ic_outline_explore
    )
    object Bookmark : BottomNavItem(
        route = Screen.Bookmark.route,
        titleRes = R.string.bookmark,
        selectedIcon = R.drawable.ic_baseline_bookmark,
        unselectedIcon = R.drawable.ic_outline_bookmark
    )
    object Stats : BottomNavItem(
        route = Screen.Stats.route,
        titleRes = R.string.stats,
        selectedIcon = R.drawable.ic_round_bar_chart,
        unselectedIcon = R.drawable.ic_round_bar_chart
    )
}