package com.example.animefacts.presentation.main.information.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.recommendation),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(animeRecs){ anime->
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