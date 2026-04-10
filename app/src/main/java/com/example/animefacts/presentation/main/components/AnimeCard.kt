package com.example.animefacts.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.animefacts.R
import com.example.animefacts.domain.model.Anime

@Composable
fun AnimeCard(
    anime: Anime,
    onAnimeClick: (Int)->Unit,
    modifier: Modifier
){
    Card(
        onClick = {onAnimeClick(anime.id)},
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.Transparent
        ),
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = MaterialTheme.shapes.medium
    ){
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ){
            Box(
                contentAlignment = Alignment.TopEnd
            ) {

                    if (anime.imageUrl.isNotBlank()){
                        AsyncImage(
                            model = anime.imageUrl.ifBlank { null },
                            contentDescription = null,
                            modifier = Modifier
                                .aspectRatio(0.7f)
                                .clip(MaterialTheme.shapes.medium),
                            contentScale = ContentScale.Crop,
                            error = painterResource(R.drawable.no_image_placeholder),
                            placeholder = painterResource(R.drawable.no_image_placeholder)
                        )


                }
                if (anime.score != "0.00"){
                    Text(
                        text = "☆ " + anime.score,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiary,
                        modifier = Modifier
                            .padding(10.dp)
                            .background(
                                color = MaterialTheme.colorScheme.tertiary.copy(0.9f),
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(vertical = 4.dp, horizontal = 6.dp)

                    )
                }

            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = anime.title,
                maxLines = 2,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                lineHeight = 16.sp,
                overflow = TextOverflow.Ellipsis,
                softWrap = true,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(R.drawable.ic_outline_calendar_today),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(0.6f),
                    modifier = Modifier.size(16.dp)

                )
                Text(
                    text = " ${anime.year} • ${anime.type}",
                    maxLines = 1,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(0.6f)
                    )
            }
        }
    }
}


@Preview
@Composable
fun AnimeCardPrev(){
    AnimeCard(anime = Anime(
        id = 1,
        title = "Attack On Titan",
        score = "4.71",
        imageUrl = "https://myanimelist.net/images/anime/1653/153899.jpg",
        type = "TV",
        year = "2005"
    ),
        modifier = Modifier,
        onAnimeClick = {})
}

@Composable
fun AnimeCard(
    id: Int,
    title: String,
    imageUrl: String,
    onAnimeClick: (Int) -> Unit,
    modifier: Modifier
){
    Card(
        onClick = {onAnimeClick(id)},
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.Transparent
        ),
        modifier = modifier
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ){


                if (imageUrl.isNotBlank()){
                    AsyncImage(
                        model = imageUrl.ifBlank { null },
                        contentDescription = null,
                        modifier = Modifier
                            .aspectRatio(0.7f)
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop,
                        error = painterResource(R.drawable.no_image_placeholder),
                        placeholder = painterResource(R.drawable.no_image_placeholder)
                    )


                }
            Text(
                text = title,
                maxLines = 2,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                lineHeight = 16.sp,
                overflow = TextOverflow.Ellipsis,
                softWrap = true,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 6.dp)
            )

        }
    }
}