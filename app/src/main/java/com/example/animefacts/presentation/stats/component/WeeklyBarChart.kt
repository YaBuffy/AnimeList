package com.example.animefacts.presentation.stats.component

import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.animefacts.domain.model.BarChartSegment

@Composable
fun WeeklyBarChart(
    segments:  List<BarChartSegment>,
    modifier: Modifier
){
    val maxVal = (segments.maxOfOrNull { it.value } ?: 0).coerceAtLeast(1)

    var animationStarted by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        animationStarted = true
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            segments.forEachIndexed { index, segment ->

                val targetHeight = if (animationStarted) {
                    segment.value.toFloat() / (maxVal)
                } else {
                    0f
                }

                    val animatedHeight = animateFloatAsState(
                    targetValue = targetHeight,
                    animationSpec = tween(
                        durationMillis = 1000,
                        easing = EaseOut,
                        delayMillis = index * 50
                ),
                label = "BarAnimation"
                )

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.height(20.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        if (segment.value > 0 && animationStarted) {
                            Text(
                                text = segment.value.toString(),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.small)
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)), // Подложка
                        contentAlignment = Alignment.BottomCenter
                    ){
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(animatedHeight.value.coerceAtLeast(0.05f))
                                .clip(MaterialTheme.shapes.small)
                                .background(MaterialTheme.colorScheme.tertiary)
                        )

                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Box(
                        modifier = Modifier.height(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = segment.label.take(3), // Берем только первые 3 буквы (Пон, Вт...)
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1
                        )
                    }

                }




            }
        }
    }
}