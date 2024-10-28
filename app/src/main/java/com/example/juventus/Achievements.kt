package com.example.juventus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class Trophy(
    val title: String,
    val iconId: Int,
    val timesWon: Int
)

@Composable
fun AchievementsScreen() {
    val trophies = listOf(
        Trophy("Serie A", R.drawable.scudetto, 38),
        Trophy("UEFA Champions League", R.drawable.uefachampions, 2),
        Trophy("Coppa Italia", R.drawable.copaitalia, 15),
        Trophy("Intercontinental Cup", R.drawable.intercontinental, 2),
        Trophy("EUFA", R.drawable.uefa, 3),
        Trophy("UEFA Super Cup", R.drawable.uefasuper, 2),
        Trophy("UEFA Cup Winners", R.drawable.uefacup, 1),
        Trophy("Intertoto Cup", R.drawable.intertoto, 1),
        Trophy("Italian Super Cup", R.drawable.italiansuper, 9),
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(trophies) { trophy ->
            TrophyListItem(trophy)
        }
    }
}


@Composable
fun TrophyListItem(trophy: Trophy) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(id = trophy.iconId),
                contentDescription = trophy.title,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = trophy.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Text(
            text = trophy.timesWon.toString(),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}


