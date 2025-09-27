package com.example.exercise3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exercise3.ui.theme.Exercise3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Exercise3Theme {
                MyComponent()
            }
        }
    }
}

@Composable
fun MyComponent() {
    Column {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Part(
                title = stringResource(R.string.t1),
                description = stringResource(R.string.d1),
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFE1D3FD))
            )
            Part(
                title = stringResource(R.string.t2),
                description = stringResource(R.string.d2),
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFD5C3FE))
            )
        }
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Part(
                title = stringResource(R.string.t3),
                description = stringResource(R.string.d3),
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFC0A9F8))
            )
            Part(
                title = stringResource(R.string.t4),
                description = stringResource(R.string.d4),
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFEEE2FD))
            )
        }
    }
}

@Composable
fun Part(title: String, description: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(text = description, textAlign = TextAlign.Justify)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Exercise3Theme {
        MyComponent()
    }
}
