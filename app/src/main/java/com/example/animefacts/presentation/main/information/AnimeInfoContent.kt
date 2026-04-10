package com.example.animefacts.presentation.main.information

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animefacts.domain.model.AnimeInfo
import com.example.animefacts.domain.model.AnimeStatistics
import com.example.animefacts.domain.model.Genre
import com.example.animefacts.domain.model.Recommendation
import com.example.animefacts.domain.model.Studio
import com.example.animefacts.presentation.main.information.component.AnimeImage
import com.example.animefacts.presentation.main.information.component.AnimeScoreStatistics
import com.example.animefacts.presentation.main.information.component.FavAndStatus
import com.example.animefacts.presentation.main.information.component.InfoCard
import com.example.animefacts.presentation.main.information.component.RecommendationRow
import com.example.animefacts.presentation.main.information.component.StatusChart
import com.example.animefacts.presentation.main.information.component.SynopsisText
import com.example.animefacts.presentation.main.information.component.TitleInfo

@Composable
fun AnimeInfoContent(
    animeInfo: AnimeInfo,
    animeStats: AnimeStatistics,
    animeRecs: List<Recommendation>,
    onAnimeClick: (Int) -> Unit,
    addToFav: () -> Unit,
    isAdded: Boolean,
    selectStatus: () -> Unit,
    modifier: Modifier = Modifier,
){
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
//            .systemBarsPadding()
            .verticalScroll(scrollState)
    ) {
        AnimeImage(imageUrl = animeInfo.imageUrl)

        Spacer(modifier = Modifier.height(20.dp))

        TitleInfo(
            title = animeInfo.title,
            englishTitle = animeInfo.englishTitle,
            rating = animeInfo.rating,
        )

        FavAndStatus(
            favorites = animeInfo.favorites.toString(),
            addToFav = addToFav,
            selectStatus = selectStatus,
            isAdded = isAdded
        )

        Spacer(modifier = Modifier.height(20.dp))

        InfoCard(
            animeInfo = animeInfo
        )

        Spacer(modifier = Modifier.height(20.dp))

        SynopsisText(
            synopsis = animeInfo.synopsis,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f),
            thickness = 0.5.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )

        AnimeScoreStatistics(
            scores = animeStats.scores,
            averageScore = animeInfo.score,
            votes = animeInfo.scoredBy,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f),
            thickness = 0.5.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        )

        StatusChart(
            watching = animeStats.watching,
            completed = animeStats.completed,
            onHold = animeStats.onHold,
            dropped = animeStats.dropped,
            planToWatch = animeStats.planToWatch,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f),
            thickness = 0.5.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        )

        RecommendationRow(
            animeRecs = animeRecs,
            onAnimeClick = onAnimeClick,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeInfoPreview(){
    AnimeInfoContent(
        modifier = Modifier,
        animeInfo = AnimeInfo(
            id = 1,
            title = "Naruto: Shippuden",
            score = "8.25",
            imageUrl = "https://cdn.myanimelist.net/images/anime/1565/111305.jpg",
            type = "TV",
            synopsis = "It has been two and a half years since Naruto Uzumaki left Konohagakure, the Hidden Leaf Village, for intense training following events which fueled his desire to be stronger. Now Akatsuki, the mysterious organization of elite rogue ninja, is closing in on their grand plan which may threaten the safety of the entire shinobi world.",
            trailerUrl = "https://www.youtube.com/watch?v=1y_XpREUjVA",
            episodes = 500,
            duration = "23 min per ep",
            status = "Finished Airing",
            scoredBy = "123",
            rating = "PG-13 - Teens 13 or older",
            members = 2500000,
            favorites = 100000,
            genres = listOf(
                Genre(id = 1, name = "Action"),
                Genre(id = 2, name = "Adventure"),
                Genre(id = 3, name = "Fantasy")
            ),
            englishTitle = "sdfs",
            rank = "1",
            season = "Winter",
            year = "2007",
            studios = listOf(
                Studio(id = 1, name = "Pierrot")
            )
        ),
        animeStats = AnimeStatistics(
            watching = 1000,
            completed = 2000,
            onHold = 4995,
            dropped = 3868,
            planToWatch = 9620,
            scores = listOf(),
        ),
        animeRecs = listOf(
            Recommendation(id = 1, title = "Naruto", imageUrl = "")
        ),
        addToFav = {},
        selectStatus = {},
        isAdded = false,
        onAnimeClick = {}
    )
}