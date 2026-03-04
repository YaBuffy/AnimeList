package com.example.animefacts.presenter

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun AnimeScreen(
    vm: AnimeViewModel = hiltViewModel()
){
    val animeList by vm.animeList.collectAsState()

    LaunchedEffect(Unit) {
        vm.loadAnime()
    }

    LazyColumn() {
        items(animeList){anime->
            Text(anime.title)
            Text(anime.type.toString())
            Text(anime.score.toString())
            Text(anime.imageUrl)
            AsyncImage(
                model = anime.imageUrl,
                contentDescription = null
            )
        }
    }
}