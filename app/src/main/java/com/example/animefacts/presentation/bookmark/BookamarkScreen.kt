package com.example.animefacts.presentation.bookmark

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.animefacts.R
import com.example.animefacts.domain.model.ViewingStatus
import com.example.animefacts.presentation.bookmark.component.VerticalBookmarksGrid
import kotlinx.coroutines.launch

@Composable
fun BookmarkScreen(
    modifier: Modifier,
    onAnimeClick: (Int) -> Unit,
    vm: BookmarkViewModel = hiltViewModel()
){
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { ViewingStatus.entries.size})

    val visible = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {visible.value = true}

    AnimatedVisibility(
        visible = visible.value,
        enter = fadeIn(animationSpec = tween(600)) +
                slideInHorizontally(initialOffsetX = { -it/5 }, animationSpec = tween(1000)),
    ){
        Column(
            modifier = modifier
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
                Tab(
                    selected = pagerState.currentPage == 0,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                page = 0,
                                animationSpec = tween(
                                    500,
                                    easing = FastOutSlowInEasing
                                )
                            )
                        }
                    },
                    text = { Text(stringResource(R.string.favorites)) },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onBackground
                )

                ViewingStatus.entries.forEachIndexed { index, tab ->
                    if (tab == ViewingStatus.NOT_WATCHED) return@forEachIndexed
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
                val itemsFlow = remember(page) {vm.getBookmarksForPage(page)}
                val items by itemsFlow.collectAsState(initial = emptyList())

                VerticalBookmarksGrid(
                    items = items,
                    onAnimeClick = onAnimeClick
                )


            }

        }

    }
}