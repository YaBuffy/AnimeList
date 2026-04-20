package com.example.animefacts.presentation.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx. compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.animefacts.presentation.main.components.FakeSearchBar
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    vm: HomeViewModel = hiltViewModel(),
    onSearch: ()->Unit,
    onAnimeClick: (Int)->Unit,
    modifier: Modifier
){
    val pagerState = rememberPagerState(pageCount = { HomeTab.entries.size })
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        topBar = { FakeSearchBar(onSearch = onSearch) }
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                edgePadding = 16.dp,
                divider = {},
                contentColor = MaterialTheme.colorScheme.primary,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                            .animateContentSize(),
                        height = 3.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            ) {
                HomeTab.entries.forEachIndexed { index, tab ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(
                                    page = tab.ordinal,
                                    animationSpec = tween(
                                        500,
                                        easing = FastOutSlowInEasing
                                    )
                                )
                            }
                        },
                        text = { Text(stringResource(tab.titleRes)) },
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                when (HomeTab.entries[page]) {
                    HomeTab.Ongoing -> OngoingAnimeScreen(onAnimeClick = onAnimeClick)
                    HomeTab.Completed -> CompletedAnimeScreen(onAnimeClick = onAnimeClick)
                    HomeTab.Upcoming -> UpcomingAnimeScreen(onAnimeClick = onAnimeClick)
                    HomeTab.Movie -> MovieAnimeScreen(onAnimeClick = onAnimeClick)
                }
            }

        }
    }
}