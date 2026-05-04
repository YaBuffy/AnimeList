package com.example.animefacts.presentation.discover

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.animefacts.R
import com.example.animefacts.presentation.discover.component.DiscoverButtonsRow
import com.example.animefacts.presentation.discover.component.ReviewCard
import com.example.animefacts.presentation.main.information.component.RecommendationRow

@Composable
fun DiscoverContent(
    modifier: Modifier,
    onAnimeClick: (Int) -> Unit,
    state: DiscoverUiState,
    loadRandomAnimeInfo: () -> Unit,
    onScheduleClick: () -> Unit,
    refresh: () -> Unit
) {
    when {
        state.isLoading ->
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        state.recommendations.isNotEmpty() -> {
            LazyColumn(
                modifier = modifier
                    .padding(horizontal = 20.dp)
            ) {
                item {
                    DiscoverButtonsRow(
                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                        onRandomClick = loadRandomAnimeInfo,
                        onScheduleClick = onScheduleClick
                    )

                }
                item {
                    RecommendationRow(
                        animeRecs = state.recommendations,
                        onAnimeClick = onAnimeClick,
                        modifier = Modifier
                    )

                }
                item{
                    Text(
                        text = stringResource(R.string.reviews),
                        modifier = Modifier.padding(vertical = 10.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }
                items(state.topReviews) { review ->
                    ReviewCard(
                        username = review.user.username,
                        animeTitle = review.animeTitle,
                        review = review.review,
                        score = review.score,
                        date = review.date,
                        isSpoiler = review.isSpoiler,
                        avatarUrl = review.avatarUrl,
                        onAnimeClick = { onAnimeClick(review.animeId) },
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            }
        }

        state.errorMessage != null -> {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = state.errorMessage)
                Button(
                    onClick = refresh
                ) {
                    Text(text = "Retry")
                }
            }
        }
    }
}