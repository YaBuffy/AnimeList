package com.example.animefacts.presenter.home

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animefacts.presenter.main.components.AnimePagingGrid

@Composable
fun CompletedAnimeScreen(
    vm: HomeViewModel = hiltViewModel()
){
    val pagingItems  = vm.completedAnime.collectAsLazyPagingItems()

    AnimePagingGrid(pagingItems)

}