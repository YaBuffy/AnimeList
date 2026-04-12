package com.example.animefacts.presentation.discover

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.animefacts.presentation.main.components.FakeSearchBar

@Composable
fun DiscoverScreen(
    vm: DiscoverViewModel = hiltViewModel(),
    onSearch: ()->Unit,
    onAnimeClick: (Int)->Unit,
    modifier: Modifier
){
    Scaffold(
        modifier = modifier,
        topBar = { FakeSearchBar(onSearch = onSearch) }
    ) { paddingValues ->
        Column (modifier = Modifier.padding(paddingValues)) {

        }
    }
}