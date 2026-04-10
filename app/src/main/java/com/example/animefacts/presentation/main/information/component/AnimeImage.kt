package com.example.animefacts.presentation.main.information.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.animefacts.R

@Composable
fun AnimeImage(
    imageUrl: String,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ){
        AsyncImage(
            model = imageUrl.ifBlank { null },
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.3f)
                .blur(4.dp),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(
                ColorMatrix().apply {
                    setToSaturation(0f)
                }
            ),
            error = painterResource(R.drawable.no_image_placeholder),
            placeholder = painterResource(R.drawable.no_image_placeholder)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to MaterialTheme.colorScheme.surface,   // верх затемнение
                            0.2f to Color.Transparent,
                            0.8f to Color.Transparent,
                            1.0f to MaterialTheme.colorScheme.surface    // низ затемнение
                        )
                    )
                )
        )
        AsyncImage(
            model = imageUrl.ifBlank { null },
            contentDescription = "Anime Poster",
            modifier = Modifier
                .align(Alignment.Center)
                .height(250.dp)
                .padding(vertical = 14.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Fit,
            error = painterResource(R.drawable.no_image_placeholder),
            placeholder = painterResource(R.drawable.no_image_placeholder)
        )
    }
}