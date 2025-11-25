package com.example.utsmobile2372015ariel.utils

import android.content.Context
import com.example.utsmobile2372015ariel.entity.Anime
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun loadAnime(context: Context, id: Number): Anime {
    val jsonFile = context.assets.open(id.toString() + ".json").bufferedReader().use {
        it.readText()
    }

    val type = object : TypeToken<Anime>() {}.type
    val anime = Gson().fromJson<Anime>(jsonFile, type)

    print(anime)
    return anime
}