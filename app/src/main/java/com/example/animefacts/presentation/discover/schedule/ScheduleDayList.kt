package com.example.animefacts.presentation.discover.schedule

import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.presentation.main.components.AnimePagingGrid

@Composable
fun ScheduleDayList(
    pagingItems: LazyPagingItems<Anime>,
    onAnimeClick: (Int) -> Unit
){
    val isRefreshing = pagingItems.loadState.refresh is LoadState.Loading

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {pagingItems.refresh()}
    ) {
        AnimePagingGrid(
            pagingItems = pagingItems,
            onAnimeClick = onAnimeClick)
    }
}
