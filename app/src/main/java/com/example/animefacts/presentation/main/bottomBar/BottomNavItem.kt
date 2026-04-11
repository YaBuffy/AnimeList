package com.example.animefacts.presentation.main.bottomBar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.animefacts.NavGraph
import com.example.animefacts.R

sealed class BottomNavItem(
    val route: String,
    @StringRes val titleRes: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
) {
    object Home : BottomNavItem(
        route = NavGraph.Home.route,
        titleRes =  R.string.home,
        selectedIcon = R.drawable.ic_baseline_home,
        unselectedIcon = R.drawable.ic_outline_home
    )
    object Discover : BottomNavItem(
        route = NavGraph.Discover.route,
        titleRes = R.string.discover,
        selectedIcon = R.drawable.ic_baseline_explore,
        unselectedIcon = R.drawable.ic_outline_explore
    )
    object Bookmark : BottomNavItem(
        route = NavGraph.Bookmark.route,
        titleRes = R.string.bookmark,
        selectedIcon = R.drawable.ic_baseline_bookmark,
        unselectedIcon = R.drawable.ic_outline_bookmark
    )
    object Stats : BottomNavItem(
        route = NavGraph.Stats.route,
        titleRes = R.string.stats,
        selectedIcon = R.drawable.ic_round_bar_chart,
        unselectedIcon = R.drawable.ic_round_bar_chart
    )
}