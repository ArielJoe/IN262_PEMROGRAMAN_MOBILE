package com.example.p11.ui.tablet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.p11.entity.Movie
import com.example.p11.ui.phone.DetailPage
import com.example.p11.viewmodel.MovieViewModel

@Composable
fun TabletPage(viewModel: MovieViewModel) {
    val movies by viewModel.movies.collectAsState()

    // State untuk menyimpan movie yang sedang dipilih (highlighted)
    var selectedMovie by remember { mutableStateOf<Movie?>(null) }

    Row(modifier = Modifier.fillMaxSize()) {

        // --- BAGIAN KIRI: DAFTAR FILM ---
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            LazyColumn {
                items(movies) { movie ->

                    // Apakah item ini sedang dipilih?
                    val isSelected = movie.id == selectedMovie?.id

                    // Tentukan warna: Biru muda jika dipilih, Putih jika tidak
                    val itemBackgroundColor =
                        if (isSelected) Color(0xFFBBDEFB) else Color(0xFFFFC0CB)

                    ListItem(
                        headlineContent = { Text(movie.title) },

                        // Menggunakan parameter colors untuk ListItem
                        colors = ListItemDefaults.colors(
                            containerColor = itemBackgroundColor
                        ),

                        modifier = Modifier
                            .clickable {
                                // Update state saat diklik
                                selectedMovie = movie
                            }
                            .padding(4.dp) // Memberi sedikit jarak antar item
                    )
                }
            }
        }

        // --- BAGIAN KANAN: DETAIL FILM ---
        Box(modifier = Modifier.weight(2f)) {
            // Menampilkan DetailPage berdasarkan movie yang dipilih
            DetailPage(movie = selectedMovie)
        }
    }
}
