package com.example.p12.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.p12.entity.Category
import com.example.p12.repository.CategoryRepository

class CategoryFormViewModel : ViewModel() {
    private val categoryRepository = CategoryRepository()
//    var categoryId by mutableStateOf(0)
//    var categoryName by mutableStateOf("")

    fun saveCategory(id: Int, name: String, onResult: () -> Unit) {
        val newCategory = Category(id, name)
        categoryRepository.addCategory(newCategory)
        onResult()
    }
}
