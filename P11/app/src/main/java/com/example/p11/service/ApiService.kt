package com.example.p11.service

import com.example.p11.entity.Movie
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie_data.json")
    suspend fun getAllMovies(): List<Movie>

    @GET("m{id}.json")
    suspend fun getMovie(@Path("id") id: Int): Movie
}