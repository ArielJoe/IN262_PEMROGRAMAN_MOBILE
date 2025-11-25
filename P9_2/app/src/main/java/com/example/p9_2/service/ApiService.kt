package com.example.p9_2.service

import com.example.p9_2.entity.MyDepartment
import retrofit2.http.GET

interface ApiService {

    @GET("get_all_departments_service.php")
    suspend fun getAllDepartments(): List<MyDepartment>
}
