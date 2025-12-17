package com.example.p11

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.p11.ui.navigation.AppNavigation
import com.example.p11.ui.tablet.TabletPage
import com.example.p11.viewmodel.MovieViewModel

@Composable
fun App(windowSizeClass: WindowSizeClass) {
    // Inisialisasi ViewModel di sini agar bisa diteruskan ke child composables
    val viewModel: MovieViewModel = viewModel()

    val width = windowSizeClass.widthSizeClass

    when (width) {
        // Jika layar kecil (HP Portrait)
        WindowWidthSizeClass.Compact -> {
            AppNavigation(viewModel = viewModel)
        }

        // Jika layar sedang atau besar (Tablet / HP Landscape / Foldable)
        WindowWidthSizeClass.Medium,
        WindowWidthSizeClass.Expanded -> {
            TabletPage(viewModel = viewModel)
        }
    }
}
