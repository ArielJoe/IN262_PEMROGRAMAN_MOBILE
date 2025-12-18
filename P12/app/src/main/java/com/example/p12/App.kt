package com.example.p12

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.p12.navigation.AppNavigation
import com.example.p12.page.LoginScreen
import com.example.p12.viewmodel.AuthViewModel

@Composable
fun App() {
    val authViewModel: AuthViewModel = viewModel()
    if (authViewModel.user == null) {
        LoginScreen()
    } else {
        AppNavigation(authViewModel)
    }
}