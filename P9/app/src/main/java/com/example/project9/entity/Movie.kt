package com.example.project9.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    @SerialName("judul") var title: String,
    @SerialName("deskripsi") var description: String,
    @SerialName("poster") var poster: String
)