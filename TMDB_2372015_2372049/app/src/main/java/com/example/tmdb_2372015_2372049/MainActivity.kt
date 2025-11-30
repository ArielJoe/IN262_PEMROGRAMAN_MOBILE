package com.example.tmdb_2372015_2372049

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.tmdb_2372015_2372049.entity.Movie
import com.example.tmdb_2372015_2372049.ui.DetailPage
import com.example.tmdb_2372015_2372049.ui.page.MainPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // State untuk menyimpan movie yang sedang dipilih
                    var selectedMovie by remember { mutableStateOf<Movie?>(null) }

                    if (selectedMovie == null) {
                        // Tampilkan Halaman UTAMA
                        MainPage(
                            onMovieClick = { movie ->
                                selectedMovie = movie // Set movie yang diklik
                            }
                        )
                    } else {
                        // Tampilkan Halaman DETAIL
                        // Kita force unwrap (!!) karena kita yakin selectedMovie tidak null di blok else ini
                        DetailPage(
                            movie = selectedMovie!!,
                            onBackClick = {
                                selectedMovie = null // Kembali ke null untuk balik ke main page
                            }
                        )
                    }
                }
            }
        }
    }
}
