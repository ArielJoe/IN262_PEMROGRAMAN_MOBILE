package com.example.utsmobile2372015ariel.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.utsmobile2372015ariel.R
import com.example.utsmobile2372015ariel.entity.Anime
import com.example.utsmobile2372015ariel.page.ui.theme.UTSMobile2372015ArielTheme
import android.R.drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.utsmobile2372015ariel.AnimeMangaItem

@Composable
fun DetailsPage(anime: Anime, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.image_uts_mobile),
            contentDescription = anime.deskripsi,
            modifier = Modifier
                .width(400.dp)
                .height(220.dp)
        )

        LazyRow(
            Modifier.padding(6.dp)
        ) {
            itemsIndexed(anime.genre) { index, singleItem ->
                Box(
                    modifier = Modifier
                        .background(color = Color(0xFA075D9F))
                        .padding(8.dp)
                ) {
                    Text(singleItem, color = Color(0xFAFFFFFF))
                }
                Spacer(Modifier.padding(4.dp))
            }
        }

        Text(anime.deskripsi)

        HorizontalDivider(
            Modifier.padding(vertical = 8.dp),
            DividerDefaults.Thickness,
            DividerDefaults.color
        )

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
            Row {
                Text("Writen by: ")
                Text(anime.penulis, fontWeight = FontWeight.Bold)
            }
            Row {
                Text("Publisher: ")
                Text(anime.studio, fontWeight = FontWeight.Bold)
            }
            Row {
                Text("Number of episodes: ")
                Text(anime.jumlah_episode, fontWeight = FontWeight.Bold)
            }
            Row {
                Text("Status: ")
                Text(anime.status, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    UTSMobile2372015ArielTheme {

    }
}