package com.example.animefacts.presenter.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animefacts.data.common.ApiResult
import com.example.animefacts.presenter.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    vm: HomeViewModel = hiltViewModel(),
    onBack: () -> Unit,
    paddingValues: PaddingValues,
){
    val animeListResult by vm.foundAnime.collectAsState()
    val query = vm.query
    val focusManager = LocalFocusManager.current

    var showSheet by remember { mutableStateOf(false) }
    if (showSheet){
        ModalBottomSheet(
            onDismissRequest = {showSheet = false}
        ){
            FilterContent(
                vm = vm,
                onApply = {
                    showSheet = false
                    vm.searchAnime() },
            )
        }

    }

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
                    onSearch = {
                        vm.searchAnime()
                        focusManager.clearFocus() },
                    animeList = animeListResult,
                    onFilter = {showSheet = true}
                )
            }

        }
        is ApiResult.ServerError -> {
            Text((animeListResult as ApiResult.ServerError).message)
        }
        is ApiResult.NetworkError -> {
            Text((animeListResult as ApiResult.NetworkError).message)
        }
        is ApiResult.UnknownError -> {
            Text((animeListResult as ApiResult.UnknownError).message)
        }

    }
}