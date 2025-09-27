package com.example.project2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project2.ui.theme.Project2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project2Theme {
                BoxExample()
                // ColumnExample()
            }
        }
    }
}

@Composable
fun ColumnExample() {
    Column(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            color = Color.Green,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Item #1",
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }
        Surface(
            color = Color.Yellow
        ) {
            Text(
                text = "Item #2",
                fontSize = 24.sp
            )
        }
        Surface(
            color = Color.Red,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Item #3",
                fontSize = 24.sp
            )
        }
    }
}

@Composable
fun BoxExample() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_bg),
            contentDescription = null,
            // modifier = Modifier.fillMaxSize()
        )
        Text(text = "This is a Box Example",
            color = Color.Red,
            fontSize = 24.sp
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Project2Theme {
        Greeting("Android")
    }
}