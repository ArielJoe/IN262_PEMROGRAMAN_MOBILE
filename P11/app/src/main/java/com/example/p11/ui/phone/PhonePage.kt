package com.example.p11.ui.phone

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.p11.entity.Movie

@Composable
fun PhonePage(
    movies: List<Movie>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn {
        items(movies) { movie ->
            ListItem(
                headlineContent = { Text(movie.title) },
                supportingContent = { Text(movie.description) },
                modifier = Modifier.clickable { onItemClick(movie.id) }
            )
        }
    }
}
