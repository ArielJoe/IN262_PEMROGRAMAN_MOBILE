package com.example.quiz_2372015.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.quiz_2372015.R
import com.example.quiz_2372015.entity.Weather

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsPage(weather: Weather, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.city_background_small),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .clipToBounds()
        )
        Row(
            modifier = Modifier
        ) {
            Text(weather.suhu.toString() + "C", fontSize = 50.sp)
            Text(" | ", fontSize = 50.sp)
            Text(weather.kelembapan.toString() + "%", fontSize = 50.sp)
        }
        Row(
            modifier = Modifier
        ) {
            Text(weather.kondisi, fontSize = 25.sp)
        }
        Row(
            modifier = Modifier
        ) {
            Text(weather.kecepatan_angin.toString() + "m/s", fontSize = 40.sp)
            Text("  ", fontSize = 50.sp)
            Text(weather.arah_angin, fontSize = 40.sp)

        }
    }
}
