package com.example.animefacts.presenter.home

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animefacts.presenter.main.components.AnimePagingGrid

@Composable
fun OngoingAnimeScreen(
    vm: HomeViewModel = hiltViewModel()
){
    val pagingItems  = vm.ongoingAnime.collectAsLazyPagingItems()

    AnimePagingGrid(pagingItems)

}