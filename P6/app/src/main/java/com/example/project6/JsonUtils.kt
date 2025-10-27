package com.example.project6

import android.content.Context
import com.example.project6.entity.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun loadMovieData(context: Context): List<Movie>? {
    val json = context.assets.open("data_movie.json").bufferedReader().use {
        it.readText()
    }
    val type = object : TypeToken<List<Movie>>() {}.type
    val movieList = Gson().fromJson<List<Movie>>(json, type)

    return movieList
}

// 2372015
// Ariel