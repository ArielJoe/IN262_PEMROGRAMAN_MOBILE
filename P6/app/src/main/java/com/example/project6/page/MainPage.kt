package com.example.project6.page

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.project6.MovieList
import com.example.project6.loadMovieData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieMainPage() {
    val ctx = LocalContext.current
    val movies = loadMovieData(ctx) ?: emptyList()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Movie List") },
                        windowInsets = androidx.compose.foundation.layout.WindowInsets(0)
                    )
                }
            ) { innerPadding ->
                MovieList(
                    movies = movies,
                    modifier = Modifier.padding(innerPadding),
                    onMovieClick = { movie ->
                        navController.navigate("details/${movie.judul}")
                    }
                )
            }
        }

        composable(
            route = "details/{judul}",
            arguments = listOf(navArgument("judul") { type = NavType.StringType })
        ) { backStackEntry ->
            val judul = backStackEntry.arguments?.getString("judul")
            val movie = movies.find { it.judul == judul }

            movie?.let {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(it.judul) },
                            navigationIcon = {
                                IconButton(onClick = { navController.navigateUp() }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            },
                            windowInsets = androidx.compose.foundation.layout.WindowInsets(0)
                        )
                    }
                ) { innerPadding ->
                    DetailsPage(
                        movie = it,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// 2372015
// Ariel