package com.example.animefacts.presentation.discover.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReviewCard(
    username: String,
    animeTitle: String,
    review: String,
    score: String,
    date: String,
    isSpoiler: Boolean,
    avatarUrl: String,
    onAnimeClick: () -> Unit,
    modifier: Modifier,
){
    Card(
        onClick = onAnimeClick,
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = MaterialTheme.shapes.medium
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Avatar(imageUrl = avatarUrl)
                Text(
                    text = "⭐ $score",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Column() {
                Text(
                    text = username,
                    style = MaterialTheme.typography.titleMedium
                )

                Row{
                    Text(
                        text = "on ",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text =  animeTitle,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                }
                ReviewText(review, isSpoiler)
                Text(
                    text = date,
                    color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.bodyMedium
                )

            }

        }
    }

}