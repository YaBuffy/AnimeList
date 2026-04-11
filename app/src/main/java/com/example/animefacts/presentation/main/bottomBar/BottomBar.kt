package com.example.animefacts.presentation.main.bottomBar

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavController){

    val screens = listOf(
        BottomNavItem.Home,
        BottomNavItem.Discover,
        BottomNavItem.Bookmark,
        BottomNavItem.Stats
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    NavigationBar {
        screens.forEach { screen->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            if (currentDestination?.hierarchy?.any { it.route == screen.route } == true)
                                screen.selectedIcon
                            else
                                screen.unselectedIcon
                        ),
                        tint = if(currentDestination?.hierarchy?.any { it.route == screen.route } == true)
                            MaterialTheme.colorScheme.primary
                        else
                                LocalContentColor.current,
                        contentDescription = null
                    )
                },
                onClick = {
                    navController.navigate(screen.route) {

                        // возвращаемся к стартовому экрану
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true

                        restoreState = true
                    }
                },
                selected = currentDestination?.hierarchy?.any {
                    it.route == screen.route
                } == true,
                label = { Text(stringResource(screen.titleRes)) }
            )
        }
    }
}