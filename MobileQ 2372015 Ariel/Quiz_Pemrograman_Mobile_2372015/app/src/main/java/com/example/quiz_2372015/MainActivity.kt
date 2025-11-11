package com.example.quiz_2372015

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quiz_2372015.entity.Weather
import com.example.quiz_2372015.page.DetailsPage
import com.example.quiz_2372015.ui.theme.Quiz_2372015Theme
import com.example.quiz_2372015.utils.loadWeatherData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Quiz_2372015Theme {
                MainPage()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {
    val ctx = LocalContext.current
    val weathers = loadWeatherData(ctx).data
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("Quiz01") },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFA9CFF8C)
                        )
                    )
                }
            ) { innerPadding ->
                WeatherList(
                    weathers = weathers,
                    modifier = Modifier.padding(innerPadding),
                    onWeatherClick = { weather -> navController.navigate("details/${weather.id}") }
                )
            }
        }
        composable(
            "details/{id}",
            listOf(navArgument("id") { type = NavType.StringType })
        ) { backstackEntry ->
            val id = backstackEntry.arguments?.getString("id")
            val weather = weathers.find { it.id.toString() == id }

            weather?.let {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text(it.kota) },
                            navigationIcon = {
                                IconButton(onClick = { navController.navigateUp() }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    DetailsPage(
                        weather = it,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherList(
    weathers: List<Weather>,
    modifier: Modifier = Modifier,
    onWeatherClick: (Weather) -> Unit
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(weathers) { index, singleItem ->
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                var color = Color(0xFFDF96FF)
                if (index % 2 == 1) {
                    color = Color(0xFF98E8FF)
                }
                WeatherItem(singleItem, onClick = { onWeatherClick(singleItem) }, color = color)
            }
        }
    }
}

@Composable
fun WeatherItem(
    weather: Weather, onClick: () -> Unit, color: Color
) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .clickable { onClick() }, elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .background(color)
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(weather.kota, fontWeight = FontWeight.Bold)
            Text("(" + weather.provinsi + ")")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Quiz_2372015Theme {
        MainPage()
    }
}