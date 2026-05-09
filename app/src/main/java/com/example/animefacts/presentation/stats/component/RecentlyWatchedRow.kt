package com.example.animefacts.presentation.stats.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animefacts.domain.model.Bookmark
import com.example.animefacts.presentation.main.components.AnimeCard

@Composable
fun RecentlyWatchedRow(
    bookmarks: List<Bookmark>,
    onAnimeClick: (Int) -> Unit
){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(bookmarks){ anime->
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