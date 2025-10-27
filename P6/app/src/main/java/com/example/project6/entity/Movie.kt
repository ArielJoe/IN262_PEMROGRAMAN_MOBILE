package com.example.project6.entity

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("judul") var judul: String,
    @SerializedName("deskripsi") var deskripsi: String,
    @SerializedName("poster") var poster: String
)

// 2372015
// Ariel