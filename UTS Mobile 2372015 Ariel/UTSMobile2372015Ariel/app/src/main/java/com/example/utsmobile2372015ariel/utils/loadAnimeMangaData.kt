package com.example.utsmobile2372015ariel.utils

import android.content.Context
import com.example.utsmobile2372015ariel.entity.Response
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun loadAnimeManga(context: Context): Response {
    val jsonFile = context.assets.open("anime_manga_list.json").bufferedReader().use {
        it.readText()
    }

    val type = object : TypeToken<Response>() {}.type
    return Gson().fromJson(jsonFile, type)
}