package com.example.project9.page

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.project9.MovieList
import com.example.project9.loadMovie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {
    val ctx = LocalContext.current
    val movies = loadMovie(ctx, "data_movie.json")
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
                        navController.navigate("details/${movie.title}")
                    }
                )
            }
        }

        composable(
            route = "details/{judul}",
            arguments = listOf(navArgument("judul") { type = NavType.StringType })
        ) { backStackEntry ->
            val judul = backStackEntry.arguments?.getString("judul")
            val movie = movies.find { it.title == judul }

            movie?.let {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(it.title) },
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
