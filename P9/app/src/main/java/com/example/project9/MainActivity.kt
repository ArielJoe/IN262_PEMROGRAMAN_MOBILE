package com.example.project9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.project9.entity.Movie
import com.example.project9.page.MainPage
import com.example.project9.ui.theme.Project9Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project9Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
//                        MovieList(
//                            movies = movies,
//                            modifier = Modifier.padding(innerPadding)
//                        )
                        MainPage()
                    }
                }
            }
        }
    }
}

@Composable
fun MovieList(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
    onMovieClick: (Movie) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(movies) { movie ->
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                MovieItem(movie, onClick = { onMovieClick(movie) })
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
//            OnlineImage(movie.poster)
            Text(
                movie.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(movie.description)
        }
    }
}

/*
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OnlineImage(imageUrl: String) {
    GlideImage(
        model = imageUrl,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.5f)
            .padding(bottom = 10.dp)
    )
}
*/