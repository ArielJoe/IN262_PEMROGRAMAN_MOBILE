package com.example.quiz_2372015.entity

data class Weather(
    var id: Number,
    var kota: String,
    var provinsi: String,
    var suhu: Number,
    var kelembapan: Number,
    var kondisi: String,
    var kecepatan_angin: Number,
    var arah_angin: String
)