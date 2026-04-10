package com.example.animefacts.presentation.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.animefacts.R

@Composable
fun FakeSearchBar(
    onSearch: () -> Unit
){
    OutlinedTextField(
        value = "",
        onValueChange = {},
        enabled = false,
        readOnly = true,
        placeholder = { Text(stringResource(R.string.search_anime)) },
        shape = MaterialTheme.shapes.extraLarge,
        colors = OutlinedTextFieldDefaults.colors().copy(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        leadingIcon = {Icon(
            painter = painterResource(R.drawable.ic_rounded_search),
            contentDescription = null
        )},
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clickable{
                onSearch()
            }
    )
}