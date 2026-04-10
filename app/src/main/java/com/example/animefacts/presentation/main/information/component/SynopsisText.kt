package com.example.animefacts.presentation.main.information.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animefacts.R

@Composable
fun SynopsisText(
    synopsis: String,
    modifier: Modifier
){
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .animateContentSize()
            .clickable{
                expanded = !expanded
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row{
                Icon(
                    painter = painterResource(id = R.drawable.ic_outline_notes),
                    contentDescription = null,
                )
                Text(
                    text = stringResource(R.string.synopsis),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(
                painter = painterResource(if(expanded) R.drawable.ic_baseline_arrow_drop_up else R.drawable.ic_baseline_arrow_drop_down),
                contentDescription = null
            )
        }
        HorizontalDivider(
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f),
            thickness = 0.5.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(
            modifier = Modifier.height(5.dp)
        )
        Text(
            text = synopsis,
            fontSize = 12.sp,
            maxLines = if(expanded) Int.MAX_VALUE else 3,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis
        )

    }
}