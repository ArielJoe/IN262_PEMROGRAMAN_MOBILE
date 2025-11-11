package com.example.quiz_2372015.utils

import android.content.Context
import com.example.quiz_2372015.entity.Response
import com.example.quiz_2372015.entity.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.io.print
import kotlin.reflect.typeOf

fun loadWeatherData(context: Context): Response {
    val jsonFile = context.assets.open("weather.json").bufferedReader().use {
        it.readText()
    }

    val type = object : TypeToken<Response>() {}.type
    return Gson().fromJson(jsonFile, type)
}