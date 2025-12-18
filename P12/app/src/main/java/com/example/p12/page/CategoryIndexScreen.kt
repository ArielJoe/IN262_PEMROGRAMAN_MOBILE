package com.example.p12.page

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.p12.entity.Category
import androidx.navigation.NavController
import com.example.p12.util.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryIndexScreen(categories: List<Category>, navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Category Data (Firebase)") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Route.CATEGORY_CREATE) }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "FAB Add")
            }
        }
    ) { innerPadding ->
        CategoryIndexContent(categories, Modifier.padding(innerPadding))
    }
}

@Composable
fun CategoryIndexContent(categories: List<Category>, modifier: Modifier) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(categories) { category ->
            Text(text = category.name)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}