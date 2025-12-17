package com.example.p11.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p11.entity.Movie
import com.example.p11.service.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies = _movies.asStateFlow()
    private val _movie = MutableStateFlow<Movie?>(null)
    val movie = _movie.asStateFlow()

    init {
        loadAllMovies()
    }

    fun loadAllMovies() {
        viewModelScope.launch {
            val response = ApiClient.instance.getAllMovies()
            _movies.value = response
        }
    }

    fun loadMovie(id: Int) {
        viewModelScope.launch {
            val response = ApiClient.instance.getMovie(id)
            _movie.value = response
        }
    }
}