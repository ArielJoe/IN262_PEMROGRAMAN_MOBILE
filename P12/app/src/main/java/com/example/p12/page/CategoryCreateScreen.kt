package com.example.p12.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.p12.entity.Category
import com.example.p12.viewmodel.CategoryFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCreateScreen(
    categoryViewModel: CategoryFormViewModel,
    navController: NavController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Category Form") }
            )
        }
    ) { innerPadding ->
        CategoryCreateContent(
            categoryViewModel,
            navController,
            Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun CategoryCreateContent(
    categoryFormViewModel: CategoryFormViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var categoryId by remember { mutableStateOf("") }
    var categoryName by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
//            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = categoryId,
            onValueChange = { categoryId = it },
            label = { Text("Category ID (Number)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = categoryName,
            onValueChange = { categoryName = it },
            label = { Text("Category Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                categoryFormViewModel.saveCategory(
                    categoryId.toInt(),
                    categoryName, {
                        navController.popBackStack()
                    }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Category")
        }
    }
}