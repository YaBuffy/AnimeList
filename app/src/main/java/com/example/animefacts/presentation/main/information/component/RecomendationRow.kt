package com.example.animefacts.presentation.main.information.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.animefacts.R
import com.example.animefacts.domain.model.Recommendation
import com.example.animefacts.presentation.main.components.AnimeCard

@Composable
fun RecommendationRow(
    animeRecs: List<Recommendation>,
    onAnimeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.recomendation),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            animeRecs.forEach { anime->
                AnimeCard(
                    id = anime.id,
                    title = anime.title,
                    imageUrl = anime.imageUrl,
                    onAnimeClick = onAnimeClick,
                    modifier = Modifier.width(140.dp)
                )
            }
        }
    }
}