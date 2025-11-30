package com.example.tmdb_2372015_2372049.ui.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_2372015_2372049.entity.Movie
import com.example.tmdb_2372015_2372049.service.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    // State untuk menyimpan list movie yang akan ditampilkan di UI
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies = _movies.asStateFlow()

    // State untuk status loading
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    // API KEY TMDB
    private val apiKey = "69e08db0c94cc4810112cfa5ef6090b1"

    // Fungsi helper untuk memanggil API berdasarkan kategori
    fun loadMovies(category: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Memilih endpoint berdasarkan kategori
                val response = when (category) {
                    "Now Playing" -> ApiClient.instance.getNowPlaying(apiKey)
                    "Popular" -> ApiClient.instance.getPopular(apiKey)
                    "Upcoming" -> ApiClient.instance.getUpcoming(apiKey)
                    else -> null
                }

                // Update data ke state jika response tidak null
                if (response != null) {
                    _movies.value = response.results
                }

            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error loading movies: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
