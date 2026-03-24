package com.example.animefacts.presenter.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.animefacts.R
import com.example.animefacts.domain.model.Anime
import com.example.animefacts.presenter.main.components.AnimePagingGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: ()-> Unit,
    onClear: () -> Unit,
    onBack: () -> Unit,
    pagingItems: LazyPagingItems<Anime>,
    onFilter: () -> Unit
){
    var expanded by remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    SearchBar(
        query = query,
        onQueryChange = {onQueryChange(it)},
        onSearch = {
            onSearch()
        },
        active = expanded,
        onActiveChange = { expanded = it },
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .focusRequester(focusRequester),
        placeholder = {Text(stringResource(R.string.search_anime))},
        leadingIcon = {
            IconButton(
                onClick = onBack
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_rounded_arrow_back),
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            Row {
                if (query.isNotBlank()){
                    IconButton(
                        onClick = onClear
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_rounded_close),
                            contentDescription = null
                        )
                    }

                }
                IconButton(
                    onClick = onFilter
                ){
                    Icon(
                        painter = painterResource(R.drawable.ic_baseline_filter),
                        contentDescription = null
                    )
                }

            }
        },
        colors = SearchBarDefaults.colors(
            containerColor = Color.Transparent
        )
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
                AnimePagingGrid(pagingItems)
            }
        }
    }
}