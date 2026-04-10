package com.example.animefacts.presentation.home

import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animefacts.presentation.main.components.AnimePagingGrid

@Composable
fun MovieAnimeScreen(
    vm: HomeViewModel = hiltViewModel(),
    onAnimeClick: (Int)->Unit
){
    val pagingItems  = vm.movieAnime.collectAsLazyPagingItems()
    val isRefreshing = pagingItems.loadState.refresh is LoadState.Loading

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {pagingItems.refresh()}
    ) {
        AnimePagingGrid(
            pagingItems = pagingItems,
            onAnimeClick = onAnimeClick
        )
    }
}