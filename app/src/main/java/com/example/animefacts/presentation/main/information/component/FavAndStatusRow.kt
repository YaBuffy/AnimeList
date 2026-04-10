package com.example.animefacts.presentation.main.information.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.animefacts.R

@Composable
fun FavAndStatus(
    favorites: String,
    addToFav: () -> Unit,
    selectStatus: ()-> Unit,
    isAdded: Boolean
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        OutlinedButton(
            modifier = Modifier.weight(1f),
            onClick = addToFav
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = if (!isAdded) painterResource(R.drawable.ic_outline_bookmark)
                    else painterResource(R.drawable.ic_baseline_bookmark),
                    contentDescription = null,
                    tint = if (isAdded) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onBackground
                )
                Text(favorites)
            }
        }
        OutlinedButton(
            modifier = Modifier.weight(1f),
            onClick = selectStatus
        ) {
            Text("Select Status")
        }
    }
}