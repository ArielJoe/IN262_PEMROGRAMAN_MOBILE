package com.example.p12.viewmodel

import androidx.lifecycle.ViewModel
import com.example.p12.entity.Category
import com.example.p12.repository.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoryViewModel : ViewModel() {
    private val categoryRepository: CategoryRepository = CategoryRepository()
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories = _categories.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        categoryRepository.getAllCategories {
            _categories.value = it
        }
    }

    fun addCategory(category: Category) {
        categoryRepository.addCategory(category)
    }
}