package com.example.animefacts.presenter.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.animefacts.presenter.main.components.AnimeVerticalGrid

@Composable
fun MovieAnimeScreen(
    vm: HomeViewModel = hiltViewModel()
){
    val animeListResult by vm.movieList.collectAsState()


    LaunchedEffect(Unit) {
        vm.loadMovie()
    }

    AnimeVerticalGrid(animeListResult)
}