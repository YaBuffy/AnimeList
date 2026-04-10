package com.example.animefacts.presentation.main.information.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animefacts.domain.model.AnimeRating

@Composable
fun TitleInfo(
    title: String,
    englishTitle: String,
    rating: String,
){
    Text(
        modifier = Modifier
            .padding(horizontal = 20.dp),
        text = englishTitle,
        fontSize = 20.sp,
        textAlign = TextAlign.Left,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(0.6f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Rating(rating = rating)

    }
}

@Composable
fun Rating(
    rating: String
){
    Box(
        modifier = Modifier.background(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = MaterialTheme.shapes.small
        )
            .padding( horizontal = 5.dp)
    ) {
        Text(
            maxLines = 1,
            text = when (rating) {
                AnimeRating.G.apiName -> "0+"
                AnimeRating.PG.apiName -> "6+"
                AnimeRating.PG_13.apiName -> "13+"
                AnimeRating.R17.apiName -> "17+"
                AnimeRating.R_PLUS.apiName -> "18+"
                AnimeRating.ALL.apiName -> "0+"
                else -> "0+"
            },
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}