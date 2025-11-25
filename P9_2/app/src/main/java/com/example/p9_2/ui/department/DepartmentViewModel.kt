package com.example.p9_2.ui.department

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p9_2.entity.MyDepartment
import com.example.p9_2.service.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DepartmentViewModel : ViewModel() {
    private val _departments = MutableStateFlow<List<MyDepartment>>(emptyList())
    val departments = _departments.asStateFlow()

    init {
        loadDepartments()
    }

    private fun loadDepartments() {
        viewModelScope.launch {
            val response = ApiClient.instance.getAllDepartments()
            _departments.value = response
        }
    }
}
