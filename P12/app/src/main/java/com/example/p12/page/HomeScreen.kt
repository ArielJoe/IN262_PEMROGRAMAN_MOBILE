package com.example.p12.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.p12.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(authViewModel: AuthViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Home Screen") }
            )
        }
    ) { innerPadding ->
        HomeContent(authViewModel, navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun HomeContent(
    authViewModel: AuthViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(text = "Welcome ${authViewModel.user?.email}")
        Button(
            onClick = { authViewModel.signOut() }
        ) {
            Text(text = "Sign Out")
        }
        Button(
            onClick = {
                navController.navigate("category_index")
            }
        ) {
            Text(text = "Open Category Screen")
        }
    }
}