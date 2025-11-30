package com.example.project9.page

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.project9.entity.Movie

@Composable
fun DetailsPage(movie: Movie, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        GlideImage(
//            model = movie.poster,
//            contentDescription = movie.judul,
//            modifier = Modifier
//                .fillMaxWidth()
//                .aspectRatio(1.5f)
//        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = movie.description,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
