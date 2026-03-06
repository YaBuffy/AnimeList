package com.example.animefacts.presenter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.Anime

@Composable
fun AnimeScreen(
    vm: AnimeViewModel = hiltViewModel()
){
    val animeListResult by vm.animeList.collectAsState()

    LaunchedEffect(Unit) {
        vm.loadAnime()
    }

    when(animeListResult){
        is ApiResult.Success ->{
            val animeList = (animeListResult as ApiResult.Success<List<Anime>>).data
            LazyColumn() {
                items(animeList){anime->
                    Text(anime.title)
                    Text(anime.type.toString())
                    Text(anime.score.toString())
                    Text(anime.imageUrl)
                    AsyncImage(
                        model = anime.imageUrl,
                        contentDescription = null,
                        modifier = Modifier.clickable{
                            vm.loadAnimeInfo(anime.id)
                        }
                    )
                }
            }
        }
        is ApiResult.NetworkError ->{
            val message = (animeListResult as ApiResult.NetworkError).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(message)
            }
        }
        is ApiResult.ServerError -> {
            val code = (animeListResult as ApiResult.ServerError).code
            val message = (animeListResult as ApiResult.ServerError).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("$code: $message")
            }
        }
        is ApiResult.UnknownError -> {
            val message = (animeListResult as ApiResult.UnknownError).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(message)
            }
        }
    }
}