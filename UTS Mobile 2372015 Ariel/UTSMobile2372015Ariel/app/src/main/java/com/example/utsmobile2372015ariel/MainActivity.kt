package com.example.utsmobile2372015ariel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.utsmobile2372015ariel.entity.AnimeManga
import com.example.utsmobile2372015ariel.page.DetailsPage
import com.example.utsmobile2372015ariel.ui.theme.UTSMobile2372015ArielTheme
import com.example.utsmobile2372015ariel.utils.loadAnime
import com.example.utsmobile2372015ariel.utils.loadAnimeManga

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UTSMobile2372015ArielTheme {
                MainPage()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {
    val ctx = LocalContext.current
    val animeMangas = loadAnimeManga(ctx).data
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("Anime and Manga List", color = Color(0xFAFFFFFF)) },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFA075D9F)
                        )
                    )
                }
            ) { innerPadding ->
                AnimeMangaList(
                    animeMangas = animeMangas,
                    modifier = Modifier.padding(innerPadding),
                    onAnimeMangaClick = { animeManga ->
                        navController.navigate("details/${animeManga.id}")
                    }
                )
            }
        }
        composable(
            "details/{id}",
            listOf(navArgument("id") { type = NavType.StringType })
        ) { backstackEntry ->
            val id = backstackEntry.arguments?.getString("id")
            val anime = loadAnime(LocalContext.current, id?.toInt() ?: 0)

            anime.let {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text(it.nama, color = Color(0xFAFFFFFF)) },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFA075D9F)
                            ),
                            navigationIcon = {
                                IconButton(onClick = {
                                    navController.navigateUp()
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = anime.deskripsi
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    DetailsPage(
                        anime = it,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AnimeMangaList(
    animeMangas: List<AnimeManga>,
    modifier: Modifier = Modifier,
    onAnimeMangaClick: (AnimeManga) -> Unit
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(animeMangas) { index, singleItem ->
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                var color = Color(0xFFDF96FF)
                if (singleItem.kategori == "Anime") {
                    color = Color(0xFF54E3FF)
                }

                AnimeMangaItem(singleItem, onClick = {
                    onAnimeMangaClick(singleItem)
                }, color = color)
            }
        }
    }
}

@Composable
fun AnimeMangaItem(
    animeManga: AnimeManga, onClick: () -> Unit, color: Color
) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .clickable {
                onClick()
            }, elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .background(color = color)
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )
                Text(animeManga.nama, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }
            HorizontalDivider(
                Modifier.padding(vertical = 8.dp),
                DividerDefaults.Thickness,
                DividerDefaults.color
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(animeManga.rating.toString(), fontWeight = FontWeight.Bold)
                Text(animeManga.tahun_terbit.toString(), fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    UTSMobile2372015ArielTheme {
        MainPage()
    }
}