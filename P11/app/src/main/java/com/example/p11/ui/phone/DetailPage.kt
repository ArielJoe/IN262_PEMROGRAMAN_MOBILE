package com.example.p11.ui.phone

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.p11.entity.Movie

@Composable
fun DetailPage(movie: Movie?, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (movie != null) {
            Text(
                text = movie.title,
                style = androidx.compose.material3.MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = movie.description)
        } else {
            Text(text = "Pilih film dari daftar (Sebelah Kiri)")
        }
    }
}
