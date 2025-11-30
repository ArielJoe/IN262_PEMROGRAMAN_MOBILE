package com.example.tmdb_2372015_2372049.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    // Kunci utamanya di sini: Gunakan '? = null'
    // Jika endpoint 'Popular' tidak mengirim field 'dates', variabel ini otomatis null tanpa error.
    // Jika endpoint 'Upcoming' mengirim field 'dates', variabel ini akan terisi.
    @SerialName("dates")
    val dates: DateRange? = null,

    @SerialName("page")
    val page: Int,

    @SerialName("results")
    val results: List<Movie>,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)
