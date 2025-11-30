package com.example.tmdb_2372015_2372049.ui.page

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.tmdb_2372015_2372049.entity.Movie
import com.example.tmdb_2372015_2372049.ui.ViewModel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = viewModel(),
    onMovieClick: (Movie) -> Unit
) {
    // State dari ViewModel
    val movies by viewModel.movies.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // State untuk Kategori
    val categories = listOf("Now Playing", "Popular", "Upcoming")
    var selectedCategory by remember { mutableStateOf(categories.first()) }
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    // Panggil API setiap kali kategori berubah
    LaunchedEffect(selectedCategory) {
        viewModel.loadMovies(selectedCategory)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TMDB Movies") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // 1. Tab Selector Kategori
            TabRow(selectedTabIndex = selectedTabIndex) {
                categories.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = {
                            selectedTabIndex = index
                            selectedCategory = title
                        },
                        text = { Text(text = title) }
                    )
                }
            }

            // 2. Konten Utama
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // Grid 2 Kolom
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(movies) { movie ->
                        MovieItem(movie = movie, onClick = { onMovieClick(movie) })
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            // Poster Image
            // TMDB Base URL untuk gambar: https://image.tmdb.org/t/p/w500/
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f) // Mengambil 80% tinggi card
            )

            // Judul & Rating
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.2f)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "⭐ ${movie.voteAverage}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
