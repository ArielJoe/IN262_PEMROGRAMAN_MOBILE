package com.example.p12.util

import com.google.firebase.database.FirebaseDatabase

object FirebaseHelper {
    val firebaseDatabase = FirebaseDatabase.getInstance()
    val categoryRef = firebaseDatabase.getReference("categories")

}