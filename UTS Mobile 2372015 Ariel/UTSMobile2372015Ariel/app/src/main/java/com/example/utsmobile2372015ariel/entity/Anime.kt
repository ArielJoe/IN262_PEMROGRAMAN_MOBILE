package com.example.utsmobile2372015ariel.entity

data class Anime(
    var id: Number,
    var nama: String,
    var kategori: String,
    var rating: Float,
    var tahun_terbit: Number,
    var deskripsi: String,
    var studio: String,
    var jumlah_episode: String,
    var penulis: String,
    var genre: List<String>,
    var status: String
)