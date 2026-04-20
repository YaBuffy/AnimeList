package com.example.animefacts.presentation.discover.schedule

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animefacts.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    modifier: Modifier,
    onBack: () -> Unit,
    onAnimeClick: (Int) -> Unit,
    vm: ScheduleViewModel = hiltViewModel()
){
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { ScheduleTab.entries.size})

    Column(
        modifier = modifier,
    ) {
        TopAppBar(
            title = { Text(stringResource(R.string.schedule)) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(painter = painterResource(R.drawable.ic_rounded_arrow_back), contentDescription = null)
                }
            }
        )
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
            ScheduleTab.entries.forEachIndexed { index, tab ->
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
            val currentDay = ScheduleTab.entries[page].apiValue

            val pagingItems = vm.getScheduleForDay(day = currentDay).collectAsLazyPagingItems()

            ScheduleDayList(
                pagingItems = pagingItems,
                onAnimeClick = onAnimeClick
            )
        }

    }
}
