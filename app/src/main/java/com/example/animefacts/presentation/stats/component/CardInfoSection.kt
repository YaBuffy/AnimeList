package com.example.animefacts.presentation.stats.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.animefacts.R
import com.example.animefacts.domain.model.ViewingStats

@Composable
fun CardInfoSection(stats: ViewingStats) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Total Episodes: ${stats.totalEpisodes}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = statsToString(stats),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun statsToString(stats: ViewingStats): String{
    when{
        stats.totalHours!=0 && stats.totalMinutes!=0 &&stats.totalDays!=0 -> return stringResource(
            R.string.time_spent_watching_days_hours_minutes,
            stats.totalDays,
            stats.totalHours,
            stats.totalMinutes
        )
        stats.totalDays == 0 -> return stringResource(
            R.string.time_spent_watching_hours_minutes,
            stats.totalHours,
            stats.totalMinutes
        )
        stats.totalHours == 0 -> return stringResource(
            R.string.time_spent_watching_minutes,
            stats.totalMinutes
        )
        else -> return stringResource(R.string.time_spent_watching_0_minutes)
    }
}