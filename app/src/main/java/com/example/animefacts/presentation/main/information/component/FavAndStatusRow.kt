package com.example.animefacts.presentation.main.information.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.animefacts.R
import com.example.animefacts.domain.model.ViewingStatus

@Composable
fun FavAndStatus(
    modifier: Modifier,
    favorites: String,
    addToFav: () -> Unit,
    onStatusSelected: (ViewingStatus)-> Unit,
    isAdded: Boolean,
    selectedStatus: ViewingStatus
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        OutlinedButton(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            onClick = addToFav,
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(
                width = 1.dp,
                color = if(isAdded) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.outline
            ),
            contentPadding = PaddingValues(horizontal = 12.dp)
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
                Text(
                    text = favorites,
                    color = if(isAdded) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.outline,
                    fontWeight = if(isAdded) FontWeight.Bold
                    else FontWeight.Normal
                )
            }
        }

        StatusDropdown(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            selectedStatus = selectedStatus,
            onSelected = onStatusSelected,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatusDropdown(
    modifier: Modifier,
    selectedStatus: ViewingStatus,
    onSelected: (ViewingStatus) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = it}
    ) {
        OutlinedButton(
            modifier = Modifier
                .menuAnchor(type = ExposedDropdownMenuAnchorType.PrimaryEditable, enabled = true)
                .fillMaxWidth()
                .fillMaxHeight(),
            onClick = {},
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(
                width = 1.dp,
                color = if(selectedStatus!= ViewingStatus.NOT_WATCHED) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.outline
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = if (selectedStatus!= ViewingStatus.NOT_WATCHED) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onBackground
            ),
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedStatus.status ,
                    maxLines = 1,
                    fontWeight = if(selectedStatus!= ViewingStatus.NOT_WATCHED) FontWeight.Bold
                    else FontWeight.Normal,
                    modifier = Modifier.weight(1f, fill = false)
                )
                Icon(
                    modifier = Modifier.rotate(if (expanded) 180f else 0f),
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_drop_down),
                    tint = if (selectedStatus!= ViewingStatus.NOT_WATCHED) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onBackground,
                    contentDescription = null
                )
            }
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            ViewingStatus.entries.forEach { status ->
                DropdownMenuItem(
                    text = { Text(status.status) },
                    onClick = {
                        onSelected(status)
                        expanded = false
                    }
                )
            }
        }
    }
}