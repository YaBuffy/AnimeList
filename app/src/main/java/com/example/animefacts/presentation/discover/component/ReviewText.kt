package com.example.animefacts.presentation.discover.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReviewText(
    text: String,
    isSpoiler: Boolean
){
    var spoiler by remember { mutableStateOf(isSpoiler) }
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
    ){
        Column(
            modifier = Modifier
                .clickable{expanded = !expanded}
                .blur(
                    if(spoiler) 3.dp else 0.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 400,

                    )
                )
        ) {
            Text(
                text = text,
                fontSize = 12.sp,
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
            )

            Text(
                text = if (expanded) "See less" else "See more",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
            )

            if (!spoiler && isSpoiler) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                        .clickable { spoiler = true }
                        .background(
                            color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.9f),
                            shape = MaterialTheme.shapes.small
                        )
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Hide spoiler",
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        fontSize = 12.sp
                    )

                }
            }
        }
        if (spoiler){
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.9f),
                        shape = MaterialTheme.shapes.small)
                    .clickable { spoiler = false },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Spoiler • Tap to reveal",
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    fontSize = 14.sp
                )
            }
        }

    }
}