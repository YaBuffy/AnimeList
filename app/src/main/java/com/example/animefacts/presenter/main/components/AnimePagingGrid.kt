package com.example.animefacts.presenter.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.animefacts.R
import com.example.animefacts.domain.model.Anime

@Composable
fun AnimePagingGrid(
    pagingItems: LazyPagingItems<Anime>
){
    when(pagingItems.loadState.refresh){
        is LoadState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is LoadState.Error ->{
            val error = (pagingItems.loadState.refresh as LoadState.Error).error
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(error.message ?: "Error")
            }
        }
        else -> if (pagingItems.itemCount == 0) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.no_results))
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(horizontal = 8.dp),
                contentPadding = PaddingValues(vertical = 8.dp), // Отступы сверху и снизу
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Отступ между колонками
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(pagingItems.itemCount) { index ->
                    pagingItems[index]?.let { anime ->
                        AnimeCard(
                            anime = anime,
                            modifier = Modifier.animateItem()
                        )
                    }
                }
            }
        }
    }

}