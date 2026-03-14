package com.example.animefacts.presenter.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.presenter.home.HomeViewModel

@Composable
fun SearchScreen(
    vm: HomeViewModel = hiltViewModel(),
    onBack: () -> Unit,
    paddingValues: PaddingValues
){
    val animeListResult by vm.upcomingAnimeList.collectAsState()
    val query = vm.query
    val focusManager = LocalFocusManager.current

    when(animeListResult){
        is ApiResult.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = paddingValues.calculateBottomPadding())
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ){
                        focusManager.clearFocus()
                    }
            ) {
                AnimeSearchBar(
                    query = query,
                    onQueryChange = {
                        vm.onQueryChange(it)
                    },
                    onBack = {onBack()},
                    onClear = {vm.onClear()},
                    onSearch = {vm.searchAnime()},
                    animeList = (animeListResult as ApiResult.Success<List<Anime>>).data
                )
            }

        }
        is ApiResult.ServerError -> {}
        is ApiResult.NetworkError -> {}
        is ApiResult.UnknownError -> {}

    }
}