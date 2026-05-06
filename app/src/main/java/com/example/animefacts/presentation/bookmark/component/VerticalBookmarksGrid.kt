package com.example.animefacts.presentation.bookmark.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animefacts.domain.model.Bookmark
import com.example.animefacts.presentation.main.components.AnimeCard

@Composable
fun VerticalBookmarksGrid(
    items: List<Bookmark>,
    onAnimeClick: (Int) -> Unit,
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(items){anime->
            AnimeCard(
                id = anime.id,
                title = anime.title,
                imageUrl = anime.imageUrl,
                onAnimeClick = onAnimeClick,
                modifier = Modifier
            )
        }
    }

}