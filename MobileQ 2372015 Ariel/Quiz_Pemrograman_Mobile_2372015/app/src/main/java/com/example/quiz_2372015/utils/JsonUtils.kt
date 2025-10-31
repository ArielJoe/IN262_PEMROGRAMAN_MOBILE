package com.example.quiz_2372015.utils

import android.content.Context
import com.example.quiz_2372015.entity.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.io.print
import kotlin.reflect.typeOf

fun loadWeatherData(context: Context): List<Weather>? {
    val json = context.assets.open("weather.json").bufferedReader().use {
        it.readText()
    }

    val type = object : TypeToken<List<Weather>>() {}.type
    val weatherList = Gson().fromJson<List<Weather>>(json, type)

    return weatherList
}