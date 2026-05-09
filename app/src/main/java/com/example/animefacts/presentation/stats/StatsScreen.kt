package com.example.animefacts.presentation.stats

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.animefacts.R
import com.example.animefacts.domain.model.ViewingStatus
import com.example.animefacts.presentation.main.information.component.StatusLegendItem
import com.example.animefacts.presentation.stats.component.AnimePieChart
import com.example.animefacts.presentation.stats.component.CardInfoSection
import com.example.animefacts.presentation.stats.component.PieChartSegment
import com.example.animefacts.presentation.stats.component.RecentlyWatchedRow
import com.example.animefacts.presentation.stats.component.WeeklyBarChart

@Composable
fun StatsScreen(
    vm: StatsViewModel = hiltViewModel(),
    onAnimeClick: (Int) -> Unit,
    onBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier
){
    val stats = vm.stats.collectAsState().value
    val watchingCount = vm.totalWatching.collectAsState().value
    val completedCount = vm.totalCompleted.collectAsState().value
    val onHoldCount = vm.totalOnHold.collectAsState().value
    val planToWatchCount = vm.totalPlanToWatch.collectAsState().value
    val droppedCount = vm.totalDropped.collectAsState().value

    val weeklyStats by vm.weeklyActivity.collectAsState()

    val recentlyWatched by vm.recentlyWatched.collectAsState()

    val chartData = listOf(
        PieChartSegment(watchingCount,  Color(0xFF2db039), ViewingStatus.WATCHING.titleRes),
        PieChartSegment(completedCount, Color(0xFF26448f), ViewingStatus.COMPLETED.titleRes),
        PieChartSegment(onHoldCount, Color(0xFFf9d457), ViewingStatus.ON_HOLD.titleRes),
        PieChartSegment(droppedCount, Color(0xFFD91010), ViewingStatus.DROPPED.titleRes),
        PieChartSegment(planToWatchCount, Color(0xFFc3c3c3), ViewingStatus.PLAN_TO_WATCH.titleRes)
    )
    val visible = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {visible.value = true}

    AnimatedVisibility(
        visible = visible.value,
        enter = fadeIn(animationSpec = tween(600)) +
                slideInHorizontally(initialOffsetX = { -it/5 }, animationSpec = tween(1000)),
        ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(R.string.your_statistics),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = stringResource(R.string.see_more),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable{onBookmarkClick()}
                )


            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    chartData.forEach {segment ->
                        StatusLegendItem(
                            label = stringResource(segment.label),
                            color = segment.color,
                            count = segment.value.toString()
                        )
                    }
                }

                AnimePieChart(
                    segments = chartData,
                    modifier = Modifier.size(150.dp)
                )

            }

            CardInfoSection(stats)

            if(weeklyStats.isNotEmpty()){
                Text(
                    text = stringResource(R.string.weekly_activity),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                WeeklyBarChart(
                    segments = weeklyStats,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }

            if (recentlyWatched.isNotEmpty()){
                Text(
                    text = stringResource(R.string.recently_watched),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                RecentlyWatchedRow(
                    bookmarks = recentlyWatched,
                    onAnimeClick = onAnimeClick
                )
            }


        }

    }


}