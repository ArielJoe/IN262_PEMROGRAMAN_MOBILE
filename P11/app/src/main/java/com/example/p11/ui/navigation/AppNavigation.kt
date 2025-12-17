package com.example.p11.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.p11.ui.phone.DetailPage
import com.example.p11.ui.phone.PhonePage
import com.example.p11.viewmodel.MovieViewModel

@Composable
fun AppNavigation(viewModel: MovieViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        // Rute Halaman Utama (List Film)
        composable("home") {
            val movies by viewModel.movies.collectAsState()

            PhonePage(
                movies = movies,
                onItemClick = { id ->
                    // Saat item diklik, pindah ke halaman detail dengan membawa ID
                    navController.navigate("detail/$id")
                }
            )
        }

        // Rute Halaman Detail (Menerima parameter ID)
        composable("detail/{id}") { backStackEntry ->
            // Mengambil ID dari argumen navigasi
            val idString = backStackEntry.arguments?.getString("id")
            val id = idString?.toIntOrNull()

            if (id != null) {
                // Meminta ViewModel memuat data berdasarkan ID
                viewModel.loadMovie(id)

                // Mengambil data movie yang sudah di-load
                val movie by viewModel.movie.collectAsState()

                // Menampilkan halaman detail
                DetailPage(movie = movie)
            }
        }
    }
}
