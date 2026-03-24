package com.example.animefacts.presenter.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animefacts.presenter.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    vm: HomeViewModel = hiltViewModel(),
    onBack: () -> Unit,
){
    val pagingItems = vm.pagingFlow.collectAsLazyPagingItems()
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
    AnimeSearchBar(
        query = query,
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
        onFilter = { showSheet = true }
    )

//    when(animeListResult){
//        is ApiResult.Success -> {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(bottom = paddingValues.calculateBottomPadding())
//                    .clickable(
//                        indication = null,
//                        interactionSource = remember { MutableInteractionSource() }
//                    ){
//                        focusManager.clearFocus()
//                    }
//            ) {
//                AnimeSearchBar(
//                    query = query,
//                    onQueryChange = {
//                        vm.onQueryChange(it)
//                    },
//                    onBack = {onBack()},
//                    onClear = {vm.onClear()},
//                    onSearch = {
//                        vm.searchAnime()
//                        focusManager.clearFocus() },
//                    pagingItems = animeListResult,
//                    onFilter = {showSheet = true}
//                )
//            }
//
//        }
//        is ApiResult.ServerError -> {
//            Text((animeListResult as ApiResult.ServerError).message)
//        }
//        is ApiResult.NetworkError -> {
//            Text((animeListResult as ApiResult.NetworkError).message)
//        }
//        is ApiResult.UnknownError -> {
//            Text((animeListResult as ApiResult.UnknownError).message)
//        }
//
//    }
}