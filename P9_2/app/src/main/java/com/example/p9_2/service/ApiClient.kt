package com.example.p9_2.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ApiClient {
    private val json = Json { ignoreUnknownKeys = true }

    private val client = OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    val instance: ApiService by lazy {
        val contentType = "application/json".toMediaType()

        Retrofit.Builder()
            .baseUrl("https://fittest.itmaranatha.org/me_mobile20172/service/")
            .addConverterFactory(json.asConverterFactory(contentType)).client(client).build()
            .create(ApiService::class.java)
    }
}