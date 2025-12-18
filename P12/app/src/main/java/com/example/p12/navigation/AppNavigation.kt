package com.example.p12.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.p12.page.CategoryCreateScreen
import com.example.p12.page.CategoryIndexScreen
import com.example.p12.page.HomeScreen
import com.example.p12.util.Route
import com.example.p12.viewmodel.AuthViewModel
import com.example.p12.viewmodel.CategoryFormViewModel
import com.example.p12.viewmodel.CategoryViewModel

@Composable
fun AppNavigation(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val categoryViewModel: CategoryViewModel = viewModel()
    val categoryFormViewModel: CategoryFormViewModel = viewModel()

    NavHost(navController, startDestination = "home") {

        composable(Route.HOME) {
            HomeScreen(
                authViewModel,
                navController = navController
            )
        }

        composable(Route.CATEGORY_INDEX) {
            val categories = categoryViewModel.categories.collectAsState().value
            CategoryIndexScreen(categories, navController)
        }

        composable(Route.CATEGORY_CREATE) {
            CategoryCreateScreen(categoryFormViewModel, navController)
        }
    }
}