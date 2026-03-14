package com.example.animefacts.presenter.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.Anime

@Composable
fun AnimeVerticalGrid(
    animeListResult: ApiResult<List<Anime>>
){
    when (animeListResult) {
        is ApiResult.Success -> {
            val animeList = animeListResult.data
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(horizontal = 8.dp),
                contentPadding = PaddingValues(vertical = 8.dp), // Отступы сверху и снизу
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Отступ между колонками
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = animeList
                ) { anime ->
                    AnimeCard(
                        anime = anime,
                        modifier = Modifier.animateItem())
                }
            }
        }

        is ApiResult.NetworkError -> {
            val message = animeListResult.message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(message)
            }
        }

        is ApiResult.ServerError -> {
            val code = animeListResult.code
            val message = animeListResult.message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("$code: $message")
            }
        }

        is ApiResult.UnknownError -> {
            val message = animeListResult.message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(message)
            }
        }

    }
}