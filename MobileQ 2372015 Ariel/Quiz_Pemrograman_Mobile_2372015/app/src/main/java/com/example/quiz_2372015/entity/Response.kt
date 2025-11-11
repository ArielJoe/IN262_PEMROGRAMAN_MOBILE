package com.example.quiz_2372015.entity

data class Response(
    var status: String,
    var message: String,
    var data: List<Weather>
)