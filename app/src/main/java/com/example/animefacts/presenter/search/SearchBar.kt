package com.example.animefacts.presenter.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.animefacts.R
import com.example.animefacts.domain.model.Anime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: ()-> Unit,
    onClear: () -> Unit,
    onBack: () -> Unit,
    animeList: List<Anime>
){
    var expanded by remember { mutableStateOf(true) }

    SearchBar(
        query = query,
        onQueryChange = {onQueryChange(it)},
        onSearch = {
            onSearch()
        },
        active = expanded,
        onActiveChange = { expanded = it },
        modifier = Modifier
            .padding(horizontal = 10.dp),
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
        },
        colors = SearchBarDefaults.colors(
            containerColor = Color.Transparent
        )
    ){
        if (animeList.isEmpty()){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        } else{
            LazyColumn() {
                items(animeList){anime->
                    Text(anime.title)
                    AsyncImage(
                        model = anime.imageUrl,
                        contentDescription = null
                    )
                }
            }

        }
    }

}

@Preview
@Composable
fun SearchBarPrev(){
    var query by remember { mutableStateOf("") }
    AnimeSearchBar(
        query = query,
        onQueryChange = {
            query = it
        },
        onSearch = {},
        onClear = {},
        onBack = {},
        animeList = emptyList()
    )
}