package com.example.animefacts.presentation.main.information.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.animefacts.R

@Composable
fun StatusChart(
    watching: Int,
    completed: Int,
    onHold: Int,
    dropped: Int,
    planToWatch: Int,
    modifier: Modifier
){
    val total = watching + completed + onHold + dropped + planToWatch

    val watchingColor = Color(0xFF2db039)
    val completedColor = Color(0xFF26448f)
    val onHoldColor = Color(0xFFf9d457)
    val droppedColor = Color(0xFFD91010)
    val planToWatchColor = Color(0xFFc3c3c3)

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.viewing_status),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .height(20.dp)
                .clip(MaterialTheme.shapes.small)
        ){
            if(total>0){
                if (completed>0) {
                    WatchStatusSegment(
                        count = completed,
                        color = completedColor,
                        modifier = Modifier.weight(completed.toFloat())
                    )
                }
                if(watching>0){
                    WatchStatusSegment(
                        count = watching,
                        color = watchingColor,
                        modifier = Modifier.weight(watching.toFloat())
                    )
                }
                if(planToWatch>0){
                    WatchStatusSegment(
                        count = planToWatch,
                        color = planToWatchColor,
                        modifier = Modifier.weight(planToWatch.toFloat())
                    )
                }
                if(onHold>0){
                    WatchStatusSegment(
                        count = onHold,
                        color = onHoldColor,
                        modifier = Modifier.weight(onHold.toFloat())
                    )
                }
                if(dropped>0){
                    WatchStatusSegment(
                        count = dropped,
                        color = droppedColor,
                        modifier = Modifier.weight(dropped.toFloat())
                    )

                }
            }
        }
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatusLegendItem(
                label = stringResource(R.string.completed_anime),
                count = completed.toString(),
                color = completedColor
            )
            StatusLegendItem(
                label = stringResource(R.string.watching),
                count = watching.toString(),
                color = watchingColor
            )
            StatusLegendItem(
                label = stringResource(R.string.plan_to_watch),
                count = planToWatch.toString(),
                color = planToWatchColor
            )
            StatusLegendItem(
                label = stringResource(R.string.on_hold),
                count = onHold.toString(),
                color = onHoldColor
            )
            StatusLegendItem(
                label = stringResource(R.string.dropped),
                count = dropped.toString(),
                color = droppedColor
            )

        }
    }
}

@Composable
fun WatchStatusSegment(
    count: Int,
    color: Color,
    modifier: Modifier
){
    if (count>0) {
        Box(
            modifier = modifier
                .fillMaxHeight()
                .background(color)
        )
    }
}

@Composable
fun StatusLegendItem(
    label: String,
    count: String,
    color: Color
){
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(MaterialTheme.shapes.small)
                .background(color)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(0.6f)
        )
        Text(
            text = count,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            )
    }
}