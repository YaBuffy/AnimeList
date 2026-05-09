package com.example.animefacts.presentation.main.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animefacts.presentation.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    vm: HomeViewModel = hiltViewModel(),
    onBack: () -> Unit,
    paddingValues: PaddingValues,
    onAnimeClick: (Int)->Unit
){
    val pagingItems = vm.pagingFlow.collectAsLazyPagingItems()
    val query = vm.query
    val focusManager = LocalFocusManager.current

    var showSheet by remember { mutableStateOf(false) }

    val visible = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {visible.value = true}

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
    AnimatedVisibility(
        visible = visible.value,
        enter = fadeIn(animationSpec = tween(600)) +
                slideInVertically(initialOffsetY = { it/5 }, animationSpec = tween(1000)),
    ){
        AnimeSearchBar(
            query = query,
            paddingValues = paddingValues,
            onQueryChange = {
                vm.onQueryChange(it)
            },
            onBack = { onBack() },
            onClear = { vm.onClear() },
            onSearch = {
                vm.searchAnime()
                focusManager.clearFocus()
            },
            pagingItems = pagingItems,
            onFilter = { showSheet = true },
            onAnimeClick = onAnimeClick
        )

    }
}