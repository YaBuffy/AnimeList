package com.example.animefacts.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animefacts.domain.model.AnimeRating
import com.example.animefacts.domain.model.AnimeStatus
import com.example.animefacts.domain.model.AnimeType
import com.example.animefacts.presentation.home.HomeViewModel

@Composable
fun FilterContent(
    vm: HomeViewModel,
    onApply: () -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        FilterDropDown(
            selected = vm.selectedType,
            options = AnimeType.entries,
            onSelected = {vm.onTypeSelected(it)},
            displayText = {it.displayName},
            label = "Type"
        )

        Spacer(Modifier.height(24.dp))

        FilterDropDown(
            selected = vm.selectedRating,
            options = AnimeRating.entries,
            onSelected = {vm.onRatingSelected(it)},
            displayText = {it.displayName},
            label = "Rating"
        )

        Spacer(Modifier.height(24.dp))

        FilterDropDown(
            selected = vm.selectedStatus,
            options = AnimeStatus.entries,
            onSelected = {vm.onStatusSelected(it)},
            displayText = {it.displayName},
            label = "Status"
        )

        Spacer(Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
        ) {
            OutlinedButton(
                onClick = { vm.clearFilters() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Clear")
            }

            Button(
                onClick = onApply,
                modifier = Modifier.weight(1f)
            ) {
                Text("Apply")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> FilterDropDown(
    label: String,
    selected: T,
    options: List<T>,
    onSelected: (T) -> Unit,
    displayText: (T) -> String
){
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = displayText(selected),
            onValueChange = {},
            label = {Text(label)},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach {option->
                DropdownMenuItem(
                    text = {Text(displayText(option))},
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}