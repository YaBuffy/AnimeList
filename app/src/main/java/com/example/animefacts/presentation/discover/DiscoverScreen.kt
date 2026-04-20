package com.example.animefacts.presentation.discover

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier,
        topBar = { FakeSearchBar(onSearch = onSearch) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            DiscoverContent(
                modifier = Modifier,
                onAnimeClick = onAnimeClick,
                recommendationList = recommendationList,
                loadRandomAnimeInfo = { vm.loadRandomAnimeInfo { id -> onAnimeClick(id) } },
                onScheduleClick = onScheduleClick
            )

        }

    }
}