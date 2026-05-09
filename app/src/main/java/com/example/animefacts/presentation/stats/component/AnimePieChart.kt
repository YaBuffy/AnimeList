package com.example.animefacts.presentation.stats.component

import androidx.annotation.StringRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun AnimePieChart(
    segments: List<PieChartSegment>,
    modifier: Modifier = Modifier
){

    val transitionProgress = remember { Animatable(0f) }

    LaunchedEffect(segments) {
        transitionProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing)
        )
    }

    val total = segments.sumOf { it.value }.toFloat()



    Canvas(
        modifier = modifier
    ) {
        val strokeWidth = size.minDimension * 0.15f
        val innerPadding = strokeWidth / 2
        val chartSize = size.minDimension - strokeWidth
        if (total == 0f) {
            drawCircle(
                color = Color.LightGray,
                radius = chartSize / 2,
                style = Stroke(width = strokeWidth)
            )
            return@Canvas

        }
        var startAngle = -90f

        segments.forEach { segment ->
            val sweepAngle = (segment.value / total) * 360f
            if (sweepAngle>0f){
                drawArc(
                    color = segment.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle * transitionProgress.value,
                    useCenter = true,
                    topLeft = Offset(innerPadding, innerPadding),
                    size = Size(chartSize, chartSize)
                )
            }
            startAngle += sweepAngle
        }
    }
}

data class PieChartSegment(
    val value: Int,
    val color: Color,
    @StringRes val label: Int
)