package com.example.tmdb_2372015_2372049.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DateRange(
    @SerialName("maximum")
    val maximum: String,

    @SerialName("minimum")
    val minimum: String
)
