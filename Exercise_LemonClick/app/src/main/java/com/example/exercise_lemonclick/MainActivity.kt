package com.example.exercise_lemonclick

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.exercise_lemonclick.ui.theme.Exercise_LemonClickTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Exercise_LemonClickTheme {
                Scaffold(
                    topBar = { TopAppBar() }
                ) { innerPadding ->
                    LemonClick(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Lemon App",
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFFE6F505)
        ),
    )
}

private fun getImage(step: Int): Int {
    return when (step) {
        0 -> R.drawable.lemon_tree
        in 1..3 -> R.drawable.lemon_squeeze
        4 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
}

private fun getText(step: Int): Int {
    return when (step) {
        0 -> R.string.first
        in 1..3 -> R.string.second
        4 -> R.string.third
        else -> R.string.fourth
    }
}

@Composable
fun LemonClick(modifier: Modifier) {
    var step by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier.background(color = Color(0xFFFAF5FF))
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier
                    .padding(30.dp)
                    .width(dimensionResource(R.dimen.box_size))
                    .height(dimensionResource(R.dimen.box_size)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFD7E1)
                ),
                shape = RoundedCornerShape(dimensionResource(R.dimen.round_padding)),

                onClick = {
                    step++
                    step %= 6

                    Log.d("LemonClick", step.toString())
                }
            ) {
                Image(
                    painter = painterResource(getImage(step)),
                    contentDescription = stringResource(getText(step)),
                    modifier = Modifier
                        .height(dimensionResource(R.dimen.image_size))
                        .width(dimensionResource(R.dimen.image_size))
                )
            }
            Text(text = stringResource(getText(step)))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Exercise_LemonClickTheme {
        LemonClick(modifier = Modifier)
    }
}