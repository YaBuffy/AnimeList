package com.example.animefacts.presentation.discover

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.animefacts.presentation.main.components.FakeSearchBar
import com.example.animefacts.presentation.main.information.component.RecommendationRow

@Composable
fun DiscoverScreen(
    vm: DiscoverViewModel = hiltViewModel(),
    onSearch: ()->Unit,
    onAnimeClick: (Int)->Unit,
    modifier: Modifier
){
    val recommendationList by vm.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = { FakeSearchBar(onSearch = onSearch) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Button(
                onClick = { vm.loadRandomAnimeInfo { id -> onAnimeClick(id) } }
            ) {
                Text(text = "Random Anime")
            }

            when (recommendationList) {
                is UiState.Loading -> CircularProgressIndicator()
                is UiState.Success -> {
                    RecommendationRow(
                        animeRecs = (recommendationList as UiState.Success).data,
                        onAnimeClick = onAnimeClick,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }

                is UiState.Error -> {
                    Text(text = (recommendationList as UiState.Error).message)
                }
            }
        }

    }
}