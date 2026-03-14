package com.example.animefacts.presenter.home

import androidx. compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.animefacts.presenter.main.components.FakeSearchBar

@Composable
fun HomeScreen(
    vm: HomeViewModel = hiltViewModel(),
    onSearch: ()->Unit,
    paddingValues: PaddingValues
){
    val homeNavController = rememberNavController()
    val tabs = listOf(
        HomeTab.Ongoing,
        HomeTab.Completed,
        HomeTab.Upcoming,
        HomeTab.Movie
    )
    val currentRoute = homeNavController.currentBackStackEntryAsState().value?.destination?.route
    val selectedTabIndex = tabs.indexOfFirst { it.route == currentRoute }

    Scaffold(
        modifier = Modifier.padding(paddingValues),
        topBar = { FakeSearchBar(onSearch = onSearch) }
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {

            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                edgePadding = 16.dp,
                divider = {},
                contentColor = MaterialTheme.colorScheme.primary,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[if (selectedTabIndex >= 0) selectedTabIndex else 0])
                    )
                },

            ) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = {
                            homeNavController.navigate(tab.route){
                                popUpTo(homeNavController.graph.startDestinationId) {
                                    saveState = true
                                }
                                // Предотвращаем множественные копии
                                launchSingleTop = true
                                // Восстанавливаем состояние при возврате
                                restoreState = true

                            }
                        },
                        text = {Text(tab.title)},
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = MaterialTheme.colorScheme.onBackground
                        )
                }
            }
            HomeTabNavigation(navController = homeNavController)

        }
    }
}