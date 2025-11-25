package com.example.p9_2.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyDepartment(
    @SerialName("id") var id: String,
    @SerialName("name") var name: String
)
