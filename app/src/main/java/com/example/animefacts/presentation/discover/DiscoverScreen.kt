package com.example.animefacts.presentation.discover

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

    val visible = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {visible.value = true}

    AnimatedVisibility(
        visible = visible.value,
        enter = fadeIn(animationSpec = tween(600)) +
                slideInHorizontally(initialOffsetX = { -it/5 }, animationSpec = tween(1000)),
    ){
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
}