package com.example.animefacts.presentation.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
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
    pagingItems: LazyPagingItems<Anime>,
    modifier: Modifier = Modifier,
    onAnimeClick: (Int)->Unit
){
    val loadState = pagingItems.loadState.refresh

    val isInitialLoading = loadState is LoadState.Loading && pagingItems.itemCount == 0
    val isInitialError = loadState is LoadState.Error && pagingItems.itemCount == 0
    Box(modifier = modifier.fillMaxSize()) {
        when {
            isInitialLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            isInitialError -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(R.string.error_network))
                    Button(onClick = { pagingItems.retry() }) {
                        Text(stringResource(R.string.action_retry))
                    }
                }
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(pagingItems.itemCount) { index ->
                        pagingItems[index]?.let { anime ->
                            AnimeCard(
                                anime = anime,
                                modifier = Modifier.animateItem(),
                                onAnimeClick = onAnimeClick
                            )
                        }
                    }
                    val appendState = pagingItems.loadState.append

                    when (appendState) {
                        is LoadState.Loading -> {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Box(Modifier.fillMaxWidth().padding(16.dp), Alignment.Center) {
                                    CircularProgressIndicator(modifier = Modifier.size(32.dp))
                                }
                            }
                        }
                        is LoadState.Error -> {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Column(
                                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(stringResource(R.string.error_failed_to_load_more))
                                    Spacer(Modifier.height(8.dp))
                                    Button(onClick = { pagingItems.retry() }) {
                                        Text(stringResource(R.string.action_tap_to_load))
                                    }
                                }
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}