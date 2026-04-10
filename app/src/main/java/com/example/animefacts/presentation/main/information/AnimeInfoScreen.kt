package com.example.animefacts.presentation.main.information

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.animefacts.R

@Composable
fun AnimeInfoScreen(
    onBack: () -> Unit,
    onAnimeClick: (Int) -> Unit,
    vm: AnimeInfoViewModel = hiltViewModel(),
    paddingValues: PaddingValues
){
    val uiState by vm.animeInfoUIState.collectAsState()

    val refreshState = rememberPullToRefreshState()

    val isRefreshing = uiState is InfoUIState.Loading

    Box(
        modifier = Modifier.fillMaxSize().padding(bottom = paddingValues.calculateBottomPadding())
    ){
            when(val state = uiState){
                is InfoUIState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is InfoUIState.Success -> {
                    AnimeInfoContent(
                        animeInfo = state.data.info,
                        animeStats = state.data.stats,
                        animeRecs = state.data.recommendations,
                        modifier = Modifier,
                        addToFav = {},
                        onAnimeClick = onAnimeClick,
                        isAdded = false,
                        selectStatus = {}
                    )
                }
                is InfoUIState.Error -> {
                         PullToRefreshBox(
                             state = refreshState,
                             isRefreshing = isRefreshing,
                             onRefresh = { vm.refreshData() },
                             modifier = Modifier.fillMaxSize(),
                         ){
                             Column(
                                 modifier = Modifier
                                     .fillMaxSize()
                                     .verticalScroll(rememberScrollState()),
                                 horizontalAlignment = Alignment.CenterHorizontally,
                                 verticalArrangement = Arrangement.Center
                             ) {
                                 Text(state.message)
                             }
                         }
                }
            }
        SmallFloatingActionButton(
            onClick = onBack,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .systemBarsPadding()
                .padding(20.dp)
                .align(Alignment.TopStart)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_rounded_arrow_back),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}