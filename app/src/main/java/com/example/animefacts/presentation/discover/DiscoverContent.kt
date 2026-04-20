package com.example.animefacts.presentation.discover

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animefacts.presentation.discover.component.DiscoverButtonsRow
import com.example.animefacts.presentation.main.information.component.RecommendationRow

@Composable
fun DiscoverContent(
    modifier: Modifier,
    onAnimeClick: (Int) -> Unit,
    recommendationList: UiState,
    loadRandomAnimeInfo: () -> Unit,
    onScheduleClick: () -> Unit
){
    Column(modifier = modifier
        .padding(horizontal = 20.dp)
    ) {
        DiscoverButtonsRow(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            onRandomClick = loadRandomAnimeInfo,
            onScheduleClick = onScheduleClick
        )

        when (recommendationList) {
            is UiState.Loading ->
                Box(
                    modifier = modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
            }
            is UiState.Success -> {
                RecommendationRow(
                    animeRecs = recommendationList.data,
                    onAnimeClick = onAnimeClick,
                    modifier = Modifier
                )
            }

            is UiState.Error -> {
                Text(text = recommendationList.message)
            }
        }
    }
}