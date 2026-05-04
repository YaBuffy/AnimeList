package com.example.animefacts.presentation.discover

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.animefacts.presentation.main.components.FakeSearchBar

@Composable
fun DiscoverScreen(
    vm: DiscoverViewModel = hiltViewModel(),
    onSearch: ()->Unit,
    onAnimeClick: (Int)->Unit,
    onScheduleClick: ()->Unit,
    modifier: Modifier
){
    val recommendationList by vm.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = { FakeSearchBar(onSearch = onSearch) }
    ) { paddingValues ->
            DiscoverContent(
                modifier = Modifier.padding(paddingValues),
                onAnimeClick = onAnimeClick,
                state = recommendationList,
                loadRandomAnimeInfo = { vm.loadRandomAnimeInfo { id -> onAnimeClick(id) } },
                onScheduleClick = onScheduleClick,
                refresh = { vm.loadAllContent() }
            )
    }
}