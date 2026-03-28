package com.example.animefacts.presenter.home

import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animefacts.presenter.main.components.AnimePagingGrid

@Composable
fun UpcomingAnimeScreen(
    vm: HomeViewModel = hiltViewModel()
){

    val pagingItems  = vm.upcomingAnime.collectAsLazyPagingItems()
    val isRefreshing = pagingItems.loadState.refresh is LoadState.Loading

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {pagingItems.refresh()}
    ) {
        AnimePagingGrid(pagingItems)
    }
}