package com.example.p12

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.p12.page.LoginScreen
import com.example.p12.viewmodel.AuthViewModel

@Composable
fun App() {
    val authViewModel: AuthViewModel = viewModel()
    if (authViewModel.user == null) {
        LoginScreen()
    } else {
        Column {
            Text(text = "Welcome ${authViewModel.user?.email}")
            Button(
                onClick = { authViewModel.signOut() }
            ) {
                Text(text = "Sign Out")
            }
        }
    }
}