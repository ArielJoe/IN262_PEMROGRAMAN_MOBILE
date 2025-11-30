package com.example.project9

import android.content.Context
import com.example.project9.entity.Movie
import kotlinx.serialization.json.Json

fun loadMovie(context: Context, fileName: String): List<Movie> {
    val jsonString = context.assets.open(fileName).bufferedReader().use {
        it.readText()
    }
    val json = Json {
        ignoreUnknownKeys = true
    }
    return json.decodeFromString(jsonString)
}
