package com.example.project6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project6.ui.theme.Project6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project6Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        CategoryLazyRow()
                        ItemLazyColumn()
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryLazyRow() {
    LazyRow {
        items(10) { index ->
            Box(modifier = Modifier.background(Color(0xFF36FF37))) {
                Text("Category #$index")
            }
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
}

@Composable
fun ItemLazyColumn() {
    LazyColumn {
        items(30) { index ->
            Box(
                modifier = Modifier
                    .background(
                        Color(0xFF29B7FF),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .fillMaxWidth()
            ) {
                Text(text = "Item #$index", modifier = Modifier.padding(15.dp))
            }
            Spacer(modifier = Modifier.height(5.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Project6Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                CategoryLazyRow()
                ItemLazyColumn()
            }
        }
    }
}

// 2372015
// Ariel